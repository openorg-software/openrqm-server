/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        //TODO: does not work currently
        return "redirect:swagger-ui.html";
    }
}

