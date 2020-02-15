/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.api;

public class NotFoundException extends ApiException {

    private int code;

    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
