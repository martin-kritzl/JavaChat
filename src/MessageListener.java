import java.io.IOException;
import java.io.ObjectInput;

/**
 * Created by mkritzl on 04.05.2016.
 */
public class MessageListener implements Runnable {

    private ObjectInput input;
    private ClientProtocol clientProtocol;

    public MessageListener(ObjectInput input, ClientProtocol clientProtocol) {
        this.input = input;
        this.clientProtocol = clientProtocol;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = (Message) input.readObject();
                clientProtocol.handleMessage(message);
            } catch (ClassNotFoundException | IOException e) {
                break;
            }
        }

    }
}
