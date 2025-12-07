package edu.elnaazk.finalproject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * PNG implementation of ImageConverter.
 * Another strategy used by ImageService.
 */
public class PngConverter implements ImageConverter {

    @Override
    public String getTargetFormatName() {
        return "PNG";
    }

    @Override
    public String getFileExtension() {
        return "png";
    }

    @Override
    public void convert(BufferedImage input, File outputFile) throws ImageConversionException {
        try {
            boolean written = ImageIO.write(input, "png", outputFile);
            if (!written) {
                throw new ImageConversionException("No writer found for PNG format");
            }
        } catch (IOException e) {
            throw new ImageConversionException("Failed to write PNG file", e);
        }
    }
}
