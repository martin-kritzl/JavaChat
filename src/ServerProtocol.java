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
        if (message.getType()==MessageType.REGISTER_USER) {
            DataHandler.getInstance().registerClient(message.getSource(), connection);
            sendToUsers(DataHandler.getInstance().getUsers(message.getSource(),null), new Message(MessageType.OK, null, null, null));
        } else if(message.getType()==MessageType.REGISTER_GROUP) {
            DataHandler.getInstance().createGroup(message.getDestination(), new ArrayList<>(Arrays.asList(message.getContent().split(" "))));
        } else if(message.getType()==MessageType.FILEUPLOAD) {
            DataHandler.getInstance().storeFile(message.getSource(),message.getDestination(),message.getContent());
        } else if (message.getType()==MessageType.FILEDOWNLOAD) {
            String content = DataHandler.getInstance().getFile(message.getSource(),message.getDestination());
            sendToUsers(DataHandler.getInstance().getUsers(message.getSource(),null), new Message(MessageType.FILEDOWNLOAD, content, null, message.getContent()));
        }

        else if(message.getType()==MessageType.TEXT) {
            List<Connection> cons = DataHandler.getInstance().getUsers(message.getDestination(),message.getSource());
            if (cons.isEmpty())
                connection.getOut().writeObject(new Message(MessageType.FALSEUSER, "User not ok", null, null));
            else
                sendToUsers(cons, message);
        }

    }

    private static void sendToUsers(List<Connection> connections, Message message) throws IOException {
        for (Connection con: connections) {
            con.getOut().writeObject(message);
        }
    }
}
