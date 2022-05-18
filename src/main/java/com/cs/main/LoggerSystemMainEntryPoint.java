package com.cs.main;

import com.cs.utils.DatabaseConnection;
import com.cs.utils.LoggerSystemParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class LoggerSystemMainEntryPoint {
    private final static Log logger = LogFactory.getLog(LoggerSystemMainEntryPoint.class);

    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            logger.error("Arguments needs be in the format --args='<File>'.");
            throw new IllegalArgumentException("Please check the arguments and try again.");
        }

        String file = args[0];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.createEventsTable();

            logger.debug("Parsing file " + file + " for events.");
            LoggerSystemParser loggerSystemParser = new LoggerSystemParser();
            loggerSystemParser.parseLogs(reader, databaseConnection);

            databaseConnection.selectAllRecords();
            databaseConnection.purgeAll();
            databaseConnection.closeDB();
        } catch (IOException e) {
            logger.error("Error parsing file ** " + file + " **");
            e.printStackTrace();
        } catch (SQLException e) {
            logger.error("Error encountered with DB");
            e.printStackTrace();
        }
    }
}
