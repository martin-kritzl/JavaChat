import java.io.Serializable;
import java.util.List;

/**
 * Created by mkritzl on 03.05.2016.
 */
public class Message implements Serializable{
    private MessageType type;
    private String content;
    private String source;
    private String destination;

    public Message(MessageType type, String content, String source, String destination) {
        this.type = type;
        this.content = content;
        this.source = source;
        this.destination = destination;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
