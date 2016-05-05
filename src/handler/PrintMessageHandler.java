package handler;

import connection.Connection;
import data.Message;

/**
 * Created by mkritzl on 05.05.2016.
 */
public class PrintMessageHandler implements MessageHandler {
    @Override
    public void handle(Message message, Connection connection) {
        System.out.println(message.getSource() + ": " + message.getContent());
    }
}
