package edu.elnaazk.finalproject;

import javafx.scene.image.Image;

import java.io.File;

public class ImageItem {
    private final File file;
    private final ImageMetadata metadata;
    private final Image thumbnail;

    public ImageItem(File file, ImageMetadata metadata, Image thumbnail) {
        this.file = file;
        this.metadata = metadata;
        this.thumbnail = thumbnail;
    }

    public File getFile() {
        return file;
    }

    public ImageMetadata getMetadata() {
        return metadata;
    }

    public Image getThumbnail() {
        return thumbnail;
    }
}