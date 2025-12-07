package edu.elnaazk.finalproject;

import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Handles all image-related operations:
 * - loading images
 * - extracting metadata
 * - creating thumbnails
 * - converting images using a converter strategy
 */
public class ImageService {

    /**
     * Loads an image from disk and extracts its metadata.
     * Also creates a thumbnail so the UI doesn't load the full file.
     */
    public ImageItem addImage(File file) throws ImageLoadException {
        if (file == null || !file.exists()) {
            throw new ImageLoadException("File does not exist");
        }

        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            throw new ImageLoadException("Failed to read image", e);
        }

        if (bufferedImage == null) {
            throw new ImageLoadException("Unsupported image format");
        }

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        long size = file.length();
        String fileType = getFileExtension(file.getName());

        ImageMetadata metadata = new ImageMetadata(width, height, size, fileType);

        // 100x100 thumbnail for list view
        Image thumbnail = new Image(file.toURI().toString(), 100, 100, true, true);

        return new ImageItem(file, metadata, thumbnail);
    }

    /**
     * Converts the selected image using the chosen converter.
     * This is where the Strategy Pattern is used.
     */
    public File convertImage(ImageItem item, ImageConverter converter, File outputFolder)
            throws ImageConversionException {

        if (item == null) {
            throw new ImageConversionException("No image item provided");
        }
        if (converter == null) {
            throw new ImageConversionException("No converter provided");
        }
        if (outputFolder == null || (!outputFolder.exists() && !outputFolder.mkdirs())) {
            throw new ImageConversionException("Output folder is not valid");
        }

        BufferedImage input;
        try {
            input = ImageIO.read(item.getFile());
        } catch (IOException e) {
            throw new ImageConversionException("Failed to read source image", e);
        }

        if (input == null) {
            throw new ImageConversionException("Unsupported source image format");
        }

        String baseName = getBaseName(item.getFile().getName());
        String extension = converter.getFileExtension();
        File outputFile = new File(outputFolder, baseName + "." + extension);

        // Uses the selected converter (JPEG or PNG)
        converter.convert(input, outputFile);
        return outputFile;
    }

    /** Extracts file extension (jpg, png, etc.) */
    private String getFileExtension(String name) {
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == name.length() - 1) {
            return "";
        }
        return name.substring(dotIndex + 1).toLowerCase();
    }

    /** Removes file extension ("photo.jpg" → "photo") */
    private String getBaseName(String name) {
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex == -1) {
            return name;
        }
        return name.substring(0, dotIndex);
    }
}
