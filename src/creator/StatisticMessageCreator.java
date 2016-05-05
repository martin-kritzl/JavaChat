package creator;

import data.Message;
import data.MessageType;

/**
 * Created by mkritzl on 05.05.2016.
 */
public class StatisticMessageCreator implements MessageCreator {
    @Override
    public Message create(String content, String source, String destination) {
        return new Message(MessageType.STATISTIC, content, source, destination);
    }
}
