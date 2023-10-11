package application.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import application.view.KarmakaMainFrameController;

public class KarmakaMainFrame extends Application implements Renderable {
    private KarmakaMainFrameController kmfc;

    private Stage primaryStage;


    public static void runApp() {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        try {

            FXMLLoader loader = new FXMLLoader(
                    KarmakaMainFrameController.class.getResource("/application/view/KarmakaMainFramePane.fxml"));

            BorderPane root = loader.load();

            Scene scene = new Scene(root, root.getPrefWidth()+20, root.getPrefHeight()+10);
            scene.getStylesheets().add(KarmakaMainFrameController.class.getResource("KarmakaMainFramePane.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("KARMAKA");
            this.primaryStage.setResizable(false);
            this.kmfc = loader.getController();
            this.kmfc.initContext(primaryStage, this);
            this.kmfc.displayDialog();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayGameStart() {
       KarmakaMainFrame.runApp();
    }
}
