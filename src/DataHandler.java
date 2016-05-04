import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by mkritzl on 03.05.2016.
 */
public class DataHandler {
    private static DataHandler ourInstance;
    private static Map<String, Connection> connections;
    private static Map<String, List<String>> groups;
    private static Map<String, String> files;

    public static DataHandler getInstance() {
        if (ourInstance==null) {
            ourInstance = new DataHandler();
            connections = new HashMap<>();
            files = new HashMap<>();
        }
        return ourInstance;
    }

    private DataHandler() {
    }

    public void storeFile(String name, String content) {
        files.put(name, content);
    }

    public String getFile(String name){
        return files.get(name);
    }

    public void registerClient(String username, Connection connection) throws IOException {
        connections.put(username, connection);
    }

    public Connection getUser(String username) {
        return connections.get(username);
    }

    public List<Connection> getGroup(String groupname) {
        List<Connection> sockets = new LinkedList<>();
        for (String user: groups.get(groupname)) {
            sockets.add(connections.get(user));
        }
        return sockets;
    }

    public void createGroup(String name, List<String> usernames) {
        groups.put(name, usernames);
    }
}
