/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.exporting;

import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.NodeTraversor;
import org.openrqm.model.RQMDocument;
import org.openrqm.model.RQMElement;
import org.openrqm.model.RQMTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

public class Exporter {
    protected static Logger logger = LoggerFactory.getLogger(Exporter.class);

    protected static final String TEMPLATE_DIR = "templates/";
    protected static final String EXPORT_DIR = "export/";
    
    protected int currentImageCount = 0;
    
    public Resource export(RQMDocument document, List<RQMElement> elements, RQMTemplate template, String exportName) throws Exception {
        return null;
    }
    
    public void transformElement(TransformationNodeVisitor transformationNodeVisitor, RQMElement element) {
        Document document = Jsoup.parseBodyFragment(element.getContent());
        if (document == null) {
            logger.info("Error while parsing the element content of elementId " + element.getId());
            return;
        }
        transformationNodeVisitor.imageCount = currentImageCount;
        transformationNodeVisitor.transformedContent = "";
        NodeTraversor.traverse(transformationNodeVisitor, document.body());
        this.currentImageCount += transformationNodeVisitor.imageCount;
        element.setContent(transformationNodeVisitor.transformedContent);
    }
}
