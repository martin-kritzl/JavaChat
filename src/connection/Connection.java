package connection;

import java.io.*;
import java.net.Socket;

/**
 * Created by mkritzl on 03.05.2016.
 */
public class Connection {

    private Socket socket;
    private ObjectOutput out;

    public Connection(Socket socket, ObjectOutput out) {
        this.socket = socket;
        this.out = out;
    }

    public ObjectOutput getOut() throws IOException {
        if (out==null)
            out = new ObjectOutputStream(socket.getOutputStream());
        return out;
    }
}
