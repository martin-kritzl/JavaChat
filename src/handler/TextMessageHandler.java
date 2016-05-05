package handler;

import connection.Connection;
import data.DataHandler;
import data.Message;
import data.MessageType;

import java.io.IOException;
import java.util.List;

/**
 * Created by mkritzl on 05.05.2016.
 */
public class TextMessageHandler implements MessageHandler {
    @Override
    public void handle(Message message, Connection connection) {

        try {
            List<Connection> cons = DataHandler.getInstance().getUsers(message.getDestination(),message.getSource());
            if (cons.isEmpty())
                connection.getOut().writeObject(new Message(MessageType.FALSEUSER, "User not ok", "[Server]", null));
            else
                sendToUsers(cons, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (DataHandler.getInstance().getUser(message.getSource())!=null)
            DataHandler.getInstance().getUser(message.getSource()).getStatistic().increaseSentMessages();
    }

    private static void sendToUsers(List<Connection> connections, Message message) throws IOException {
        for (Connection con: connections) {
            con.getOut().writeObject(message);
        }
    }
}
