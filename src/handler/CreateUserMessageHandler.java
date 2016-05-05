package handler;

import connection.Connection;
import data.DataHandler;
import data.Message;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import java.io.IOException;

/**
 * Created by mkritzl on 05.05.2016.
 */
public class CreateUserMessageHandler implements MessageHandler{
    @Override
    public void handle(Message message, Connection connection) {
        try {
            DataHandler.getInstance().registerClient(message.getSource(), connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
