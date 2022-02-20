package bd.edu.seu;

import java.io.IOException;
import java.time.LocalTime;

import javafx.fxml.FXML;

public class MainMenuController {
    @FXML
    private void insertHandler() throws IOException {
        App.setRoot("insert");

    }
    @FXML
    private void informationHandler() throws IOException {
        App.setRoot("information");
    }
    @FXML
    private void aboutHandler() throws IOException {
        App.setRoot("about");
    }

}