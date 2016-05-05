package creator;

import data.Message;
import data.MessageType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by mkritzl on 05.05.2016.
 */
public class FileuploadMessageCreator implements MessageCreator{
    @Override
    public Message create(String content, String source, String destination) {
        try {
            return new Message(MessageType.FILEUPLOAD, new String(Files.readAllBytes(new File(content).toPath())), source, content.substring(content.lastIndexOf("/")+1,content.length()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
