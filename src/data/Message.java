package data;

import java.io.Serializable;

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

    public String getContent() {
        return content;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Type: " + type + "\r\nContent: " + content + "\r\nSource: "  + source + "\r\nDestination: "  + destination + "\r\n";
    }

}
