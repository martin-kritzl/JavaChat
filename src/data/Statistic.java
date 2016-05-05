package data;

/**
 * Created by mkritzl on 04.05.2016.
 */
public class Statistic {
    private String name;
    private int sentMessages;
    private int bytes;
    private int files;

    public Statistic(String name) {
        this.name = name;
    }

    public void addBytes(int bytes) {
        this.bytes += bytes;
    }

    public void increaseSentMessages() {
        sentMessages++;
    }

    public void increaseFiles() {
        this.files++;
    }

    @Override
    public String toString() {
        String out = "Name: " + name + "\r\n";
        out += "Bytes sent: " + bytes + "\r\n";
        out += "Files downloaded: " + files + "\r\n";
        out += "Sent Messages: " + sentMessages + "\r\n";
        return out;
    }
}
