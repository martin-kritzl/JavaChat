package handler;

import connection.Connection;
import creator.StatisticMessageCreator;
import data.DataHandler;
import data.Message;

import java.io.IOException;

/**
 * Created by mkritzl on 05.05.2016.
 */
public class StatisticMessageHandler implements MessageHandler {

    @Override
    public void handle(Message message, Connection connection) {
        try {
            connection.getOut().writeObject(new StatisticMessageCreator().create(DataHandler.getInstance().getUser(message.getSource()).getStatistic().toString(), "[Server]", message.getSource()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
