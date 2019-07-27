/*
openrqm-server
Entry point of the OpenRQM server implementation
SPDX-License-Identifier: GPL-2.0-only
Copyright (C) 2019 Marcel Jaehn
*/

package org.openrqm.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The OpenRQM server for requirements managing.
 */
public final class OpenRQMServer {
    final static Logger logger = LogManager.getLogger(OpenRQMServer.class);

    private static Connection connection = null;

    /**
     * Starts the OpenRQM server.
     * 
     * @param args The commandline arguments
     */
    public static void main(String[] args) {
        logger.info("Starting the OpenRQM server");

        try {
            // load the mysql driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost/openrqm?user=openrqm");
        } catch (ClassNotFoundException e) {
            logger.fatal("Could not find the mysql connector for jdbc");
            return;
        } catch (SQLException e) {
            logger.fatal("Could not connect to database: " + e.getMessage());
            return;
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id") + ", " + "email: " + resultSet.getString("email")
                        + ", " + "name: " + resultSet.getString("name") + " " + resultSet.getString("surname"));
            }
        } catch (SQLException e) {
            logger.error("A SQL exception occured: " + e.getMessage());
        }

        logger.info("Stopping the OpenRQM server");
    }
}
