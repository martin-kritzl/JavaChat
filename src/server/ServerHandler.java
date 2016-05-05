package server;

import connection.Connection;
import data.Message;

import java.io.*;
import java.net.Socket;

/**
 * Created by mkritzl on 03.05.2016.
 */
public class ServerHandler implements Runnable {

    private Socket socket;
    private ServerProtocol protocol;

    public ServerHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.protocol = new ServerProtocol();
    }

    @Override
    public void run() {
            try {
                ObjectInput input = new ObjectInputStream(socket.getInputStream());
                ObjectOutput output = new ObjectOutputStream(socket.getOutputStream());

                while(true) {
                    Message message = (Message) input.readObject();
                    protocol.handleMessage(message, new Connection(socket, output));
                }

            } catch (ClassNotFoundException | IOException e) {

            }
    }
}
