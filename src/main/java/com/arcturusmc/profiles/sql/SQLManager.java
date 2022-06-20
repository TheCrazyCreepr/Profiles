package com.arcturusmc.profiles.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLManager {

    private String host, database, username, password;
    private int port;

    public static Connection connection;

    public SQLManager() {
        host = "arcturusmc.com";
        port = 3306;
        database = "profiletester";
        username = "arcturus";
        password = "@6vV2$T1iKyClEGc";
    }

    public void openConnection() throws SQLException {
        if(connection != null && !connection.isClosed()) {
            return;
        }

        connection = DriverManager.getConnection("jdbc:mysql://" +
                this.host + ":" + this.port + "/" + this.database, this.username, this.password);
        System.out.println("connected successfully");
    }

    public Connection getConnection() {
        return connection;
    }

    public static PreparedStatement prepareStatement(String query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
        } catch (SQLException x) {
            x.printStackTrace();
        }
        return ps;
    }

    public boolean isConnectionClosed() {
        try {
            return getConnection().isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void reopenConnection() {
        if(isConnectionClosed()) {
            try {
                openConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
