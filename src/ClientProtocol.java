import java.io.IOException;

/**
 * Created by mkritzl on 03.05.2016.
 */
public class ClientProtocol {
    private String chatroom;
    private String username;

    public void handleMessage(Message message) throws IOException {
        if (message.getType()==MessageType.OK) {
            System.out.println("Registration ok");
        } else if(message.getType()==MessageType.TEXT || message.getType()==MessageType.FALSEUSER) {
            System.out.println(message.getContent());
        }
    }

    public Message handleInput(String input) {
        Message message = null;
        if (input==null || input.equals("")) return null;
        if (input.charAt(0)=='/') {
            String[] args = input.split(" ");
            String command = args[0].substring(1, args[0].length());
            if (command.equals("fileupload")) {
                message = new Message(MessageType.FILEUPLOAD, null, username, null);
            } else if(command.equals("fileupload")) {
                message = new Message(MessageType.FILEDOWNLOAD,null, username, null);
            } else if(command.equals("join")) {
                chatroom = args[1];
            }
        } else {
            message = new Message(MessageType.TEXT, input, username, chatroom);
        }
        return message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
