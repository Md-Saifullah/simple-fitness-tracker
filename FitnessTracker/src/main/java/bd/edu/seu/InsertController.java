package bd.edu.seu;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class InsertController implements Initializable {
    @FXML
    private DatePicker datePicker;
    @FXML
    ChoiceBox<String> hourChoiceBox;
    @FXML
    ChoiceBox<String> minuteChoiceBox;
    @FXML
    TextField weightTextField;
    @FXML
    TextField systolicTextField;
    @FXML
    TextField diastolicTextField;
    @FXML
    Label warningLabel;

    DBActions dbActions;

    @FXML
    private void handleBackButton() throws IOException {
        App.setRoot("mainMenu");
    }


    public InsertController() {

        dbActions = new DBActions();
        datePicker = new DatePicker();
        hourChoiceBox = new ChoiceBox<>();
        minuteChoiceBox = new ChoiceBox<>();
        weightTextField = new TextField();
        systolicTextField = new TextField();
        diastolicTextField = new TextField();
        warningLabel = new Label();
    }
    public ArrayList<String> getHours(){
        ArrayList<String> hours=new ArrayList<>();
        for (char i='0';i<='2';i++){
            char k='9';
            if(i=='2'){
                k='3';
            }
            for(char j='0';j<=k;j++){
                String hour=i+""+j;
                hours.add(hour);
            }
        }
        return hours;
    }
    public ArrayList<String> getMinutes(){
        ArrayList<String> minutes=new ArrayList<>();
        for (char i='0';i<='5';i++){
            for(char j='0';j<='9';j++){
                String minute=i+""+j;
                minutes.add(minute);
            }
        }
        return minutes;
    }

    public void setTimeChoiceBox(){
        ObservableList<String> hours= FXCollections.observableArrayList();
        hours.addAll(getHours());
        hourChoiceBox.setItems(hours);
        hourChoiceBox.setValue("00");
        ObservableList<String> minutes= FXCollections.observableArrayList();
        minutes.addAll(getMinutes());
        minuteChoiceBox.setItems(minutes);
        minuteChoiceBox.setValue("00");
    }

    @FXML
    private void handleResetButton(){
        reset();
    }
    public void reset(){
        datePicker.setValue(LocalDate.now());
        hourChoiceBox.setValue("00");
        minuteChoiceBox.setValue("00");
        weightTextField.clear();
        systolicTextField.clear();
        diastolicTextField.clear();
    }

    @FXML
    private void handleInsertButton() {
        LocalDate date = datePicker.getValue();
        LocalTime time=LocalTime.parse(hourChoiceBox.getValue()+":"+minuteChoiceBox.getValue());
        String weight = weightTextField.getText();
        String systolic = systolicTextField.getText();
        String diastolic = diastolicTextField.getText();
        System.out.println(date+" "+time);

        if (date==null||weight.isEmpty() || systolic.isEmpty() || diastolic.isEmpty()) {
            System.out.println("empty");
            warningLabel.setText("Fill all the fields!");
        } else {
            System.out.println("not empty");
            warningLabel.setText("");

            Record record = new Record(date,
                    Double.parseDouble(weight),
                    time,
                   Integer.parseInt(systolic),
                    Integer.parseInt(diastolic));

            if (dbActions.insertRecord(record)) {
                System.out.println("success");
                reset();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful");
                alert.setHeaderText(null);
                alert.setContentText("Record inserted!");

                alert.showAndWait();
            } else {
                warningLabel.setText("Insertion failed. Check the formats of your inserted values!");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTimeChoiceBox();
        datePicker.setValue(LocalDate.now());

    }
}
