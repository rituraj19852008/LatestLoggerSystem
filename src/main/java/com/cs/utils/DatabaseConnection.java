package com.cs.utils;

import com.cs.mappers.LogEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;


public class DatabaseConnection {
    private static final Log logger = LogFactory.getLog(DatabaseConnection.class);

    private static final String tableName = "Events";

    private static Connection connection;

    public DatabaseConnection() throws SQLException {
        String connectionString = "jdbc:hsqldb:file:hsqldb/logs";

        logger.info("Opening database connection at < hsqldb/logs >");
        connection = DriverManager.getConnection(connectionString, "SA", "");
    }

    public void createEventsTable() throws SQLException {
        String createEvents = "CREATE TABLE IF NOT EXISTS Events (id VARCHAR(100) NOT NULL, duration FLOAT NOT NULL, " +
                "type VARCHAR(100), host VARCHAR(100), alert BOOLEAN NOT NULL)";

        logger.info("Creating Events table");
        connection.createStatement().executeUpdate(createEvents);
    }

    void saveEvent(LogEvent logEvent) throws SQLException {
        String addEvent = "INSERT INTO " + tableName + " (id, duration, type, host, alert)  VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(addEvent);
        preparedStatement.setString(1, logEvent.getId());
        preparedStatement.setFloat(2, logEvent.getDuration());
        preparedStatement.setString(3, logEvent.getType());
        preparedStatement.setString(4, logEvent.getHost());
        preparedStatement.setBoolean(5, logEvent.isAlert());

        preparedStatement.executeUpdate();
    }

    public void closeDB() throws SQLException {
        logger.info("Closing Database connection.");
        connection.close();
    }

    public void selectAllRecords() throws SQLException {
        String getAll = "SELECT * FROM " + tableName;

        logger.debug("Retrieving all DB entries from " + tableName + "  table.");
        ResultSet resultSet = connection.createStatement().executeQuery(getAll);

        while (resultSet.next()) {
            if (resultSet.getBoolean(5)) {
                logger.debug("Alert for EventID --" + resultSet.getString(1) + "--");
            }
        }
    }

    public void purgeAll() throws SQLException {
        String deleteAll = "DELETE FROM " + tableName;
        logger.info("Deleting all entries in DB table < " + tableName + " >.");
        connection.createStatement().executeUpdate(deleteAll);
    }
}

