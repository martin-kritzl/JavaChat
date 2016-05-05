package handler;

import connection.Connection;
import data.Message;

/**
 * Created by mkritzl on 05.05.2016.
 */
public class PrintStatistikHandler implements MessageHandler {
    @Override
    public void handle(Message message, Connection connection) {
        System.out.println(message.getContent());
    }
}
