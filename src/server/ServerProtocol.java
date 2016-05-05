package server;

import connection.Connection;
import data.DataHandler;
import data.Message;
import handler.*;
import java.io.IOException;

public class ServerProtocol {
    public void handleMessage(Message message, Connection connection) throws IOException {
        System.out.println(message.toString());
        MessageHandler handler;
        switch (message.getType()) {
            case REGISTER_USER:
                handler = new CreateUserMessageHandler();
                break;
            case REGISTER_GROUP:
                handler = new CreateGroupMessageHandler();
                break;
            case FILEDOWNLOAD:
                handler = new FiledownloadMessageHandler(DataHandler.getInstance().getServerStorage());
                break;
            case FILEUPLOAD:
                handler  = new FileuploadMessageHandler(DataHandler.getInstance().getServerStorage());
                break;
            case STATISTIC:
                handler  = new StatisticMessageHandler();
                break;
            case TEXT:
                handler = new TextMessageHandler();
                break;
            default: return;
        }
        handler.handle(message, connection);
        if (message.getContent()!=null)
            DataHandler.getInstance().getUser(message.getSource()).getStatistic().addBytes(message.getContent().getBytes().length);
    }
}
