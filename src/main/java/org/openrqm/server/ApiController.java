/*
 * openrqm-server
 * Workspaces resource to return a nested list of workspaces as json object
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}