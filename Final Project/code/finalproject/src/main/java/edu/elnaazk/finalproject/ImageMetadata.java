package edu.elnaazk.finalproject;

/**
 * Simple data class that stores the image's width, height,
 * file size, and format. Used to display info in the UI.
 */
public class ImageMetadata {
    private final int width;
    private final int height;
    private final long fileSize;
    private final String fileType;

    public ImageMetadata(int width, int height, long fileSize, String fileType) {
        this.width = width;
        this.height = height;
        this.fileSize = fileSize;
        this.fileType = fileType;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileType() {
        return fileType;
    }
}
