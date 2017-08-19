package javagui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javagui.view.DialogFehlerController;

public class MainApp extends Application {

    private static Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
        MainApp.primaryStage.setTitle("Epas");
        // Set the application icon:
        MainApp.primaryStage.getIcons().add(new Image("file:resources/images/icon_epas_robot.png"));

        initRootLayout();

        showInterface();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the main Interface inside the root layout.
     */
    public void showInterface() {
        try {
            // Load Interface.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Interface.fxml"));
            AnchorPane interFace = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(interFace);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void showDialogFeler(String fehlerart) {
        try {
            // Load the fxml file and create a new stage for the dialog-fehler.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/DialogFehler.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Fehler bearbeiten");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);

            // Set the fehlerart into the controller.
            DialogFehlerController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.fehlerKorrigieren(fehlerart);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException e){e.printStackTrace();} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}