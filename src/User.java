import java.util.HashMap;
import java.util.Map;

/**
 * Created by mkritzl on 04.05.2016.
 */
public class User {
    private Connection connection;
    private Map<String, String> files;

    public User(Connection connection) {
        this.connection = connection;
        this.files = new HashMap<>();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public void setFiles(Map<String, String> files) {
        this.files = files;
    }
}
