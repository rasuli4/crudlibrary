package com.sda.database.connection;

import com.sda.database.property.ConnectionProperty;
import lombok.extern.java.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@Log
public abstract class DatabaseConnection {

    private Connection connection = null;

    public ConnectionProperty getConnectionProperty(final String fileName) {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ConnectionProperty.builder()
                .databaseURL(properties.getProperty("database.url"))
                .driverName(properties.getProperty("database.driver"))
                .userName(properties.getProperty("database.username"))
                .password(properties.getProperty("database.password")).build();
    }

    public void open(final ConnectionProperty connectionProperty) {
        try {
            connection = DriverManager.getConnection(connectionProperty.getDatabaseURL(),
                    connectionProperty.getUserName(), connectionProperty.getPassword());
            log.info("Connection established over: " + connectionProperty.getDriverName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    abstract void connect();

    public void close() {
        try {
            if (!connection.isClosed()) {
                connection.close();
                log.info("Connection is closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet read(final String sql) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            return statement.executeQuery(sql);

        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

}
