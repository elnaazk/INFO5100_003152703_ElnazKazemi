package edu.elnaazk.finalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.List;

/**
 * Controller for the main UI.
 * Handles user actions: loading images, showing details, and converting.
 */
public class MainController {

    @FXML private Button addImagesButton;
    @FXML private ListView<ImageItem> imageListView;
    @FXML private ImageView previewImageView;
    @FXML private Label widthLabel;
    @FXML private Label heightLabel;
    @FXML private Label sizeLabel;
    @FXML private Label typeLabel;
    @FXML private ComboBox<String> formatComboBox;
    @FXML private Button convertButton;

    // Handles image loading + conversion logic
    private final ImageService imageService = new ImageService();

    // List used by the ListView
    private final ObservableList<ImageItem> items =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind list to the UI ListView
        imageListView.setItems(items);

        // Custom cell to show thumbnails instead of plain text
        imageListView.setCellFactory(listView -> new ListCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(ImageItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(item.getThumbnail());
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                    imageView.setPreserveRatio(true);
                    setText(item.getFile().getName());
                    setGraphic(imageView);
                }
            }
        });

        // Update metadata panel when the user selects an image
        imageListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldItem, newItem) -> showImageDetails(newItem)
        );

        // Allow user to pick output format
        formatComboBox.setItems(FXCollections.observableArrayList("JPEG", "PNG"));
        formatComboBox.getSelectionModel().selectFirst();
    }

    /**
     * Opens a file chooser and loads chosen images.
     * Errors are caught and shown using alerts.
     */
    @FXML
    public void onAddImages() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Images");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        Window window = addImagesButton.getScene().getWindow();
        List<File> selected = chooser.showOpenMultipleDialog(window);

        if (selected == null || selected.isEmpty()) return;

        for (File file : selected) {
            try {
                items.add(imageService.addImage(file));
            } catch (ImageLoadException e) {
                showError("Could not load image: " + file.getName(), e.getMessage());
            }
        }
    }

    /**
     * Converts the currently selected image using the chosen format.
     */
    @FXML
    public void onConvert() {
        ImageItem selectedItem = imageListView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showError("No image selected", "Please select an image to convert.");
            return;
        }

        String format = formatComboBox.getSelectionModel().getSelectedItem();
        if (format == null) {
            showError("No format selected", "Please choose a target format.");
            return;
        }

        // Strategy Pattern: dynamically choose a converter
        ImageConverter converter =
                "JPEG".equalsIgnoreCase(format) ? new JpegConverter() :
                        new PngConverter();

        DirectoryChooser directoryChooser = new DirectoryChooser();
        Window window = convertButton.getScene().getWindow();
        File outputFolder = directoryChooser.showDialog(window);
        if (outputFolder == null) return;

        try {
            File outputFile = imageService.convertImage(selectedItem, converter, outputFolder);
            showInfo("Conversion successful", "Image saved to:\n" + outputFile.getAbsolutePath());
        } catch (ImageConversionException e) {
            showError("Conversion failed", e.getMessage());
        }
    }

    /**
     * Shows preview and metadata when user selects an image.
     */
    private void showImageDetails(ImageItem item) {
        if (item == null) {
            previewImageView.setImage(null);
            widthLabel.setText("");
            heightLabel.setText("");
            sizeLabel.setText("");
            typeLabel.setText("");
            return;
        }

        Image image = new Image(item.getFile().toURI().toString());
        previewImageView.setImage(image);

        ImageMetadata meta = item.getMetadata();
        widthLabel.setText(String.valueOf(meta.getWidth()));
        heightLabel.setText(String.valueOf(meta.getHeight()));

        long sizeBytes = meta.getFileSize();
        String sizeText =
                sizeBytes < 1024 ? sizeBytes + " B" :
                        sizeBytes < 1024 * 1024 ? (sizeBytes / 1024) + " KB" :
                                (sizeBytes / (1024 * 1024)) + " MB";

        sizeLabel.setText(sizeText);
        typeLabel.setText(meta.getFileType());
    }

    /** Shows error pop-up */
    private void showError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /** Shows confirmation pop-up */
    private void showInfo(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}