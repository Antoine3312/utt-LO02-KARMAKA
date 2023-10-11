package application.view;

import application.control.KarmakaMainFrame;
import application.control.Renderable;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class KarmakaMainFrameController implements Initializable {


    private Stage primaryStage;
    private KarmakaMainFrame kmf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initContext(Stage _containingStage, KarmakaMainFrame _karmakaMainFrame) {
        this.primaryStage = _containingStage;
        this.kmf = _karmakaMainFrame;
    }

    public void displayDialog() {
        this.primaryStage.show();
    }
}
