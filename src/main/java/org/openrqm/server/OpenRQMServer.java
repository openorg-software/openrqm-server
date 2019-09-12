/*
 * openrqm-server
 * Entry point of the OpenRQM server implementation
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * The OpenRQM server for requirements managing.
 */
@SpringBootApplication
public class OpenRQMServer {
    final static Logger logger = LogManager.getLogger(OpenRQMServer.class);

    private static Connection connection = null;

    /**
     * Starts the OpenRQM server.
     * 
     * @param args The commandline arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(OpenRQMServer.class, args);

        logger.info("Starting the OpenRQM server");

        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost/openrqm?user=openrqm");
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
