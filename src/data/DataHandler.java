package data;

import connection.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by mkritzl on 03.05.2016.
 */
public class DataHandler {
    private static DataHandler ourInstance;
    private static Map<String, User> connections;
    private static Map<String, List<String>> groups;

    public static DataHandler getInstance() {
        if (ourInstance==null) {
            ourInstance = new DataHandler();
            connections = new HashMap<>();
            groups = new HashMap<>();
        }
        return ourInstance;
    }

    private DataHandler() {
    }

    public void storeFile(String user, String name, String content) {
        connections.get(user).getFiles().put(name, content);
    }

    public String getFile(String username, String name){
        return connections.get(username).getFiles().get(name);
    }

    public void registerClient(String username, Connection connection) throws IOException {
        connections.put(username, new User(username, connection));
    }

    public User getUser(String name) {
        return connections.get(name);
    }

    public List<Connection> getUsers(String name, String without) {
        List<Connection> cons = new LinkedList<>();
        if (groups.get(name)!=null)
            for (String user: groups.get(name)) {
                if (!user.equals(without))
                    cons.add(connections.get(user).getConnection());
            }
        if (connections.get(name)!=null && !connections.get(name).equals(without))
            cons.add(connections.get(name).getConnection());
        return cons;
    }

    public void createGroup(String name, List<String> usernames) {
        groups.put(name, usernames);
    }
}
