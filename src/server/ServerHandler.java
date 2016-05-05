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

    public ServerHandler(Socket socket) throws IOException {
        this.socket = socket;
    }

    @Override
    public void run() {
            try {
                System.out.println("Listen Message");
                ObjectInput input = new ObjectInputStream(socket.getInputStream());
                ObjectOutput output = new ObjectOutputStream(socket.getOutputStream());

                while(true) {
                    Message message = (Message) input.readObject();
                    System.out.println("Server: " + message.getContent());
                    ServerProtocol.handleMessage(message, new Connection(socket, input, output));
                }

            } catch (ClassNotFoundException | IOException e) {

            }
    }
}
