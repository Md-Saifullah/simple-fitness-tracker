package bd.edu.seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class AboutController {
    @FXML
    private void handleBackButton() throws IOException {
        App.setRoot("mainMenu");
    }
}
