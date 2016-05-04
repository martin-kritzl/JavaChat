import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mkritzl on 03.05.2016.
 */
public class Server {
    private ServerSocket socket;
    private boolean running;

    public Server(int port) throws IOException {
        this.socket = new ServerSocket(port);
    }

    public void startListening() throws IOException {
        this.running = true;
        while (this.running) {
            Socket socket = this.socket.accept();
            new Thread(new ServerHandler(socket)).start();
        }
    }

    public void stopListening() {
        this.running = false;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(12345);
        server.startListening();
    }
}
