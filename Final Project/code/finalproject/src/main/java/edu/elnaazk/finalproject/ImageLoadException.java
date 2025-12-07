package edu.elnaazk.finalproject;

/**
 * Custom exception used when loading an image fails.
 * This helps the controller show a clear error instead of crashing.
 */
public class ImageLoadException extends Exception {

    public ImageLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageLoadException(String message) {
        super(message);
    }
}