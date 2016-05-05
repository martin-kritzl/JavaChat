package client;

import creator.*;
import data.DataHandler;
import data.Message;
import handler.*;

import java.io.IOException;

/**
 * Created by mkritzl on 03.05.2016.
 */
public class ClientProtocol {
    private String chatroom;
    private String username;

    public void handleMessage(Message message) throws IOException {
        MessageHandler handler = null;
        switch (message.getType()) {
            case TEXT: handler = new PrintMessageHandler(); break;
            case STATISTIC: handler = new PrintStatistikHandler(); break;
            case FILEUPLOAD: handler = new FileuploadMessageHandler(DataHandler.getInstance().getClientStorage());break;
            default:
                System.out.println(message.getContent());
                return;
        }
        if (handler!=null)
            handler.handle(message, null);
    }

    public Message handleInput(String input) {
        MessageCreator messageCreator = null;
        if (input==null || input.equals("")) return null;
        if (input.charAt(0)=='/') {
            String command = input.split(" ")[0];
            command = command.substring(1, command.length());
            input = input.substring(input.indexOf(" ")+1, input.length());
            switch (command) {
                case "fileupload":
                    messageCreator = new FileuploadMessageCreator();
                    input = DataHandler.getInstance().getClientStorage()+input;
                    break;
                case "filedownload":
                    messageCreator = new FiledownloadMessageCreator();break;
                case "create_group":
                    messageCreator = new CreateGroupMessageCreator();break;
                case "statistic":
                    messageCreator = new StatisticMessageCreator();break;
                case "join":
                    chatroom = input;
                    return null;
                case "exit":
                    System.exit(0);
                    break;
                default: return null;
            }
        } else {
            messageCreator = new TextMessageCreator();
        }
        return messageCreator.create(input, username, chatroom);

    }

    public void setUsername(String username) {
        this.username = username;
    }
}
