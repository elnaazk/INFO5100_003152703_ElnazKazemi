package edu.elnaazk.finalproject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * JPEG implementation of the ImageConverter interface.
 * This is one "strategy" in the Strategy Pattern.
 */
public class JpegConverter implements ImageConverter {

    @Override
    public String getTargetFormatName() {
        return "JPEG";
    }

    @Override
    public String getFileExtension() {
        return "jpg";
    }

    @Override
    public void convert(BufferedImage input, File outputFile) throws ImageConversionException {
        try {
            boolean written = ImageIO.write(input, "jpg", outputFile);
            if (!written) {
                throw new ImageConversionException("No writer found for JPEG format");
            }
        } catch (IOException e) {
            throw new ImageConversionException("Failed to write JPEG file", e);
        }
    }
}