package Proj.dbexamples.db;

import Proj.dbexamples.constants.DBConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBConnection {
    INSTANCE;
    private Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:postgresql://" + DBConstants.DB_HOST + ":" + DBConstants.PORT + "/"
            + DBConstants.DB, DBConstants.USER, DBConstants.PASSWORD);
        }
        return connection;
    }

}
