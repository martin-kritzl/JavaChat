package client;

import creator.CreateUserMessageCreator;
import data.Message;

import java.io.*;
import java.net.Socket;

/**
 * Created by mkritzl on 03.05.2016.
 */
public class Client {

    private Socket socket;
    private ObjectOutput out;
    private ObjectInput in;
    private String host;
    private int port;
    private ClientProtocol clientProtocol;
    private BufferedReader userinput;

    public Client(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        this.socket = new Socket(this.host, this.port);
        this.out = new ObjectOutputStream(this.socket.getOutputStream());
        this.in = new ObjectInputStream(this.socket.getInputStream());
        this.clientProtocol = new ClientProtocol();
        userinput = new BufferedReader(new InputStreamReader(System.in));
    }

    public void register(String username) throws IOException, ClassNotFoundException {
        this.out.writeObject(new CreateUserMessageCreator().create(username, null,null));
    }

    public void userInput() throws IOException {
        while (true) {
            String line = userinput.readLine();
            Message output = clientProtocol.handleInput(line);
            if (output != null) {
                this.out.writeObject(output);
            }
        }
    }

    public ClientProtocol getClientProtocol() {
        return clientProtocol;
    }

    public void listenMessage() {
        new Thread(new MessageListener(in, clientProtocol)).start();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client(args[0], Integer.parseInt(args[1]));
        client.listenMessage();
        client.register(args[2]);
        client.getClientProtocol().setUsername(args[2]);
        client.userInput();
    }
}
