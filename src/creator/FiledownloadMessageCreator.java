package creator;

import data.Message;
import data.MessageType;

/**
 * Created by mkritzl on 05.05.2016.
 */
public class FiledownloadMessageCreator implements MessageCreator {
    @Override
    public Message create(String content, String source, String destination) {
        return new Message(MessageType.FILEDOWNLOAD, content, source, null);
    }
}
