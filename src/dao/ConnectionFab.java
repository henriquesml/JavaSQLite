package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFab {
    private static ConnectionFab instance;


    private ConnectionFab() {}

    public static ConnectionFab getInstance() {
        if(instance == null) {
            instance = new ConnectionFab();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:banco_de_dados.db");
        }catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return conn;
    }
}

