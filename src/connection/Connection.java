package connection;

import java.io.*;
import java.net.Socket;

/**
 * Created by mkritzl on 03.05.2016.
 */
public class Connection {

    private Socket socket;
    private ObjectInput in;
    private ObjectOutput out;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
    }

    public Connection(Socket socket, ObjectInput in, ObjectOutput out) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutput getOut() throws IOException {
        if (out==null)
            out = new ObjectOutputStream(socket.getOutputStream());
        return out;
    }

    public ObjectInput getIn() throws IOException {
        if (in==null)
            in = new ObjectInputStream(socket.getInputStream());
        return in;
    }

}
