package handler;

import connection.Connection;
import data.DataHandler;
import data.Message;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mkritzl on 05.05.2016.
 */
public class CreateGroupMessageHandler implements MessageHandler {
    @Override
    public void handle(Message message, Connection connection) {
        DataHandler.getInstance().createGroup(message.getDestination(), new ArrayList<>(Arrays.asList(message.getContent().split(" "))));
    }
}
