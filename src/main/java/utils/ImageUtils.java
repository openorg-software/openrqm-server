/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

public class ImageUtils {
    
    public static BufferedImage dataUriToBufferedImage(String dataUrl) throws IOException {
        String encodingPrefix = ";base64,";
        int contentStartIndex = dataUrl.indexOf(encodingPrefix) + encodingPrefix.length();
        byte[] imageData = Base64.getDecoder().decode(dataUrl.substring(contentStartIndex));

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));
        
        return image;
    }
}
