package com.cs.utils;

import com.google.gson.Gson;
import com.cs.mappers.LogEvent;
import com.cs.mappers.ServerLog;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;

public class LoggerSystemParser {
    public void parseLogs(BufferedReader reader, DatabaseConnection databaseConnection) throws IOException, SQLException {
        HashMap<String, ServerLog> eventMap = new HashMap<>();
        Gson gson = new Gson();
        String line;
        while ((line = reader.readLine()) != null) {
            ServerLog log = gson.fromJson(line, ServerLog.class);
            String eventId = log.getId();
            if (!eventMap.containsKey(eventId)) {
                eventMap.put(eventId, log);
                continue;
            }

            ServerLog previousLog = eventMap.remove(eventId);
            long duration = Math.abs(log.getTimeStamp() - previousLog.getTimeStamp());
            boolean alert = false;
            if (duration > 4) {
                alert = true;
            }

            LogEvent logEvent = new LogEvent.Builder(eventId, duration, alert)
                    .withHost(log.getHost())
                    .withType(log.getType())
                    .build();
            databaseConnection.saveEvent(logEvent);
        }
    }
}
