package edu.elnaazk.finalproject;

import java.awt.image.BufferedImage;
import java.io.File;

public interface ImageConverter {
    String getTargetFormatName();
    String getFileExtension();
    void convert(BufferedImage input, File outputFile) throws ImageConversionException;
}
