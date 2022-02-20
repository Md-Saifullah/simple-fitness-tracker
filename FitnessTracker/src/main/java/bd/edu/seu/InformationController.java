package bd.edu.seu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class InformationController implements Initializable{
    @FXML
    private TableView<Record> tableView;
    @FXML
    private TableColumn<Record, LocalDate>dateColumn;
    @FXML
    private TableColumn<Record, LocalTime>timeColumn;
    @FXML
    private TableColumn<Record,Number>weightColumn;
    @FXML
    private TableColumn<Record,Number>systolicColumn;
    @FXML
    private TableColumn<Record,Number>diastolicColumn;

    DBActions dbActions;

    public InformationController() {
        dbActions=new DBActions();

        tableView=new TableView<>();
        dateColumn=new TableColumn<>();
        timeColumn=new TableColumn<>();
        weightColumn=new TableColumn<>();
        systolicColumn=new TableColumn<>();
        diastolicColumn=new TableColumn<>();
    }
    @FXML
    private void handleBackButton(){
        try {
            App.setRoot("mainMenu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Record> records= FXCollections.observableArrayList();
        records.addAll(dbActions.getRecords());
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        systolicColumn.setCellValueFactory(new PropertyValueFactory<>("systolic"));
        diastolicColumn.setCellValueFactory(new PropertyValueFactory<>("diastolic"));
        tableView.setItems(records);

    }
}
