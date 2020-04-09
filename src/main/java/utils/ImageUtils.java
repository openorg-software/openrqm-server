/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);
    
    public static BufferedImage dataUriToBufferedImage(String dataUri) throws IOException {
        String[] splittedDataUri = dataUri.split(";base64,");
        byte[] imageData = Base64.getDecoder().decode(splittedDataUri[1]);
        return ImageIO.read(new ByteArrayInputStream(imageData));
    }
    
    public static void saveImage(String dataUrl, String imageType, String filename) {
        try {
            BufferedImage image = dataUriToBufferedImage(dataUrl);
            filename = replaceInvalidFilenameCharacters(filename);
            File imageFile = new File("export/images/" + filename + "." + imageType);
            imageFile.getParentFile().mkdirs();
            ImageIO.write(image, imageType, imageFile);
        } catch (Exception ex) {
            logger.error("Could not save image from element");
            ex.printStackTrace();
        }
    }
    
    public static String replaceInvalidFilenameCharacters(String filename) {
        return filename
                .replace("\n", "")
                .replace("\r", "")
                .replace("/", "")
                .replace("\\", "")
                .replace("?", "")
                .replace("*", "")
                .replace(":", "")
                .replace("|", "")
                .replace('"'+"", "")
                .replace("<", "")
                .replace(">", "")
                .replace(".", "")
                .replace(" ", "_");
    }
    
    public static String dataUriToImageType(String dataUri) {
        dataUri = dataUri.replace("data:image/", "");
        String[] splittedDataUri = dataUri.split(";");
        return splittedDataUri[0];
    }
}
