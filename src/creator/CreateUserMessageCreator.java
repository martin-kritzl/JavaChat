package creator;

import data.Message;
import data.MessageType;

/**
 * Created by mkritzl on 05.05.2016.
 */
public class CreateUserMessageCreator implements MessageCreator {
    @Override
    public Message create(String content, String source, String destination) {
        return new Message(MessageType.REGISTER_USER, null, content, null);
    }
}
