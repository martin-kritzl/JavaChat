package handler;

import connection.Connection;
import data.Message;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mkritzl on 05.05.2016.
 */

public class FileuploadMessageHandler implements MessageHandler {

    private String path;

    public  FileuploadMessageHandler(String path) {
        this.path = path;
    }

    @Override
    public void handle(Message message, Connection connection) {
        try (FileOutputStream fos = new FileOutputStream(path + message.getDestination())){
            fos.write(message.getContent().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
