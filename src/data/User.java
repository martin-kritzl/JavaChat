package data;

import connection.Connection;

import java.util.HashMap;
import java.util.Map;

public class User {
    private Connection connection;
    private Statistic statistic;

    public User(String username, Connection connection) {
        this.connection = connection;
        this.statistic = new Statistic(username);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statistic getStatistic() {
        return statistic;
    }
}
