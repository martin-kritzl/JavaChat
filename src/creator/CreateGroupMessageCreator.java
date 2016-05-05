package creator;

import data.Message;
import data.MessageType;

/**
 * Created by mkritzl on 05.05.2016.
 */
public class CreateGroupMessageCreator implements MessageCreator {
    @Override
    public Message create(String content, String source, String destination) {
        return new Message(MessageType.REGISTER_GROUP, content.substring(content.indexOf(" ")+1,content.length()), source, content.substring(0, content.indexOf(" ")));
    }
}
