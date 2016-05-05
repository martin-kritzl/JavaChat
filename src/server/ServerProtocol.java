package server;

import connection.Connection;
import data.DataHandler;
import data.Message;
import data.MessageType;
import data.User;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mkritzl on 03.05.2016.
 */
public class ServerProtocol {

    public static void handleMessage(Message message, Connection connection) throws IOException {
        if (message.getType()== MessageType.REGISTER_USER) {
            DataHandler.getInstance().registerClient(message.getSource(), connection);
            sendToUsers(DataHandler.getInstance().getUsers(message.getSource(),null), new Message(MessageType.OK, null, "[Server]", null));
        } else if(message.getType()== MessageType.REGISTER_GROUP) {
            DataHandler.getInstance().createGroup(message.getDestination(), new ArrayList<>(Arrays.asList(message.getContent().split(" "))));
        } else if(message.getType()== MessageType.FILEUPLOAD) {
            DataHandler.getInstance().storeFile(message.getSource(),message.getDestination(),message.getContent());
            getActualUser(message).getStatistic().increaseFiles();
        } else if (message.getType()== MessageType.FILEDOWNLOAD) {
            String content = DataHandler.getInstance().getFile(message.getSource(),message.getDestination());
            sendToUsers(DataHandler.getInstance().getUsers(message.getSource(),null), new Message(MessageType.FILEDOWNLOAD, content, "[Server]", message.getContent()));
        } else if(message.getType()== MessageType.STATISTIC) {
            sendToUsers(DataHandler.getInstance().getUsers(message.getSource(),null), new Message(MessageType.STATISTIC, getActualUser(message).getStatistic().toString(), "[Server]", message.getContent()));
        }

        else if(message.getType()== MessageType.TEXT) {
            List<Connection> cons = DataHandler.getInstance().getUsers(message.getDestination(),message.getSource());
            if (cons.isEmpty())
                connection.getOut().writeObject(new Message(MessageType.FALSEUSER, "User not ok", "[Server]", null));
            else
                sendToUsers(cons, message);
                getActualUser(message).getStatistic().increaseSentMessages();
        }
        if (message.getContent()!=null)
            getActualUser(message).getStatistic().addBytes(message.getContent().getBytes().length);


    }

    private static User getActualUser(Message message) {
        return DataHandler.getInstance().getUser(message.getSource());
    }

    private static void sendToUsers(List<Connection> connections, Message message) throws IOException {
        for (Connection con: connections) {
            con.getOut().writeObject(message);
        }
    }
}
