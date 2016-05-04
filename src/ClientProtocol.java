import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * Created by mkritzl on 03.05.2016.
 */
public class ClientProtocol {
    private String chatroom;
    private String username;

    public void handleMessage(Message message) throws IOException {
        if (message.getType()==MessageType.OK) {
            System.out.println("Registration ok");
        } else if(message.getType()==MessageType.TEXT || message.getType()==MessageType.FALSEUSER || message.getType()==MessageType.STATISTIC) {
            System.out.println(message.getSource() + ": " + message.getContent());
        } else if (message.getType()==MessageType.FILEDOWNLOAD) {
            System.out.println("Saved file");
            FileOutputStream out = new FileOutputStream(new File(message.getDestination()));
            out.write(message.getContent().getBytes());
            out.close();
        }
    }

    public Message handleInput(String input) throws IOException {
        Message message = null;
        if (input==null || input.equals("")) return null;
        if (input.charAt(0)=='/') {
            String[] args = input.split(" ");
            String command = args[0].substring(1, args[0].length());
            if (command.equals("filedownload")) {
                message = new Message(MessageType.FILEDOWNLOAD, args[1] , username, args[2]);
            } else if(command.equals("fileupload")) {
                message = new Message(MessageType.FILEUPLOAD,new String(Files.readAllBytes(new File(args[1]).toPath())), username, args[2]);
            } else if(command.equals("join")) {
                chatroom = args[1];
            } else if (command.equals("create_group")) {
                String users = args[2];
                for (int i = 3; i<args.length; i++) {
                    users+=" " + args[i];
                }
                message = new Message(MessageType.REGISTER_GROUP, users, username, args[1]);
            } else if (command.equals("statistic")) {
                message = new Message(MessageType.STATISTIC, null, username, null);
            } else if (command.equals("quit") || command.equals("exit")) {
                System.exit(0);
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
