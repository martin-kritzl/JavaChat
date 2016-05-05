package handler;

import connection.Connection;
import creator.FileuploadMessageCreator;
import data.DataHandler;
import data.Message;

import java.io.IOException;

/**
 * Created by mkritzl on 05.05.2016.
 */
public class FiledownloadMessageHandler implements MessageHandler {

    private String path;

    public FiledownloadMessageHandler(String path) {
        this.path = path;
    }

    @Override
    public void handle(Message message, Connection connection) {
        Message msgout = new FileuploadMessageCreator().create(path + message.getContent(), "[Server]", message.getSource());
        DataHandler.getInstance().getUser(message.getSource()).getStatistic().increaseFiles();
        try {
            connection.getOut().writeObject(msgout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
