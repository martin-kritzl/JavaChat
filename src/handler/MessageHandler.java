package handler;

import connection.Connection;
import data.Message;

public interface MessageHandler {

    public void handle(Message message, Connection connection);

}
