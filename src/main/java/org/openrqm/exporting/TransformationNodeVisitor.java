/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.exporting;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformationNodeVisitor implements NodeVisitor {
    protected static Logger logger = LoggerFactory.getLogger(TransformationNodeVisitor.class);
    
    List<String> images = new ArrayList<>();
    int imageCount = 0;
    String transformedContent = "";

    @Override
    public void head(Node arg0, int arg1) {}

    @Override
    public void tail(Node arg0, int arg1) {}
}
