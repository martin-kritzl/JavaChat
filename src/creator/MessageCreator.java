package creator;

import data.Message;

/**
 * Created by mkritzl on 05.05.2016.
 */
public interface MessageCreator {
    public Message create(String content, String source, String destination);
}
