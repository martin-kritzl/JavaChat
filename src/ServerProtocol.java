import java.io.IOException;

/**
 * Created by mkritzl on 03.05.2016.
 */
public class ServerProtocol {

    public static void handleMessage(Message message, Connection connection) throws IOException {
        if (message.getType()==MessageType.REGISTER_USER) {
            DataHandler.getInstance().registerClient(message.getSource(), connection);
            DataHandler.getInstance().getUser(message.getSource()).getOut().writeObject(new Message(MessageType.OK, null, null, null));
        } else if(message.getType()==MessageType.REGISTER_GROUP) {
//            DataHandler.getInstance().createGroup(message.getDestination(), message.getContent().to);
        } else if(message.getType()==MessageType.TEXT) {
            Connection con = DataHandler.getInstance().getUser(message.getDestination());
            if (con==null)
                connection.getOut().writeObject(new Message(MessageType.FALSEUSER, "User not ok", null, null));
            else
                con.getOut().writeObject(message);
        }

    }
}
