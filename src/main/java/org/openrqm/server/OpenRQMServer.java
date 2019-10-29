/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.server;

import org.openrqm.export.PdfExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The OpenRQM server for requirements managing.
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "org.openrqm.server", "org.openrqm.controller", "org.openrqm.api", "org.openrqm.config", })
public class OpenRQMServer implements CommandLineRunner {
    private final static Logger logger = LoggerFactory.getLogger(OpenRQMServer.class);

    /**
     * Starts the OpenRQM server.
     * 
     * @param args The commandline arguments
     */
    public static void main(String[] args) {
        try {
            PdfExporter.export();
            //new SpringApplication(OpenRQMServer.class).run(args);
        } catch (Exception ex) {
            logger.error("An internal error occured: " + ex.getMessage());
        }
    }

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
