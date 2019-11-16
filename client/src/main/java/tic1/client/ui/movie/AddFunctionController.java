package tic1.client.ui.movie;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.models.Actor;
import tic1.client.models.Funcion;
import tic1.client.models.Genre;
import tic1.client.models.Movie;
import tic1.client.services.MovieRestTemplate;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class AddFunctionController implements Initializable {
    @Autowired
    private MovieRestTemplate movieRestTemplate;

    @FXML
    private JFXComboBox<String> cinemaName;

    @FXML
    private JFXComboBox<String> salaName;

    @FXML
    private JFXComboBox<String> movieName;

    @FXML
    private DatePicker datePicker;

    @FXML
    private JFXComboBox<LocalTime> timePicker;

    private ObservableList<LocalDate> selectedDates = FXCollections.observableArrayList();

    private boolean isEditing;
    private Funcion funcionForEdit;

    @FXML
    void addFunction(ActionEvent event) {
        String cinema = cinemaName.getSelectionModel().getSelectedItem();
        String sala = salaName.getSelectionModel().getSelectedItem();
        List<LocalDate> dates = selectedDates;
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       /* cinemaName.setItems();
        salaName.setItems();*/

        datePicker.setOnAction(event -> selectedDates.add(datePicker.getValue()));

        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        boolean alreadySelected = selectedDates.contains(item);
                        setDisable(alreadySelected);
                        setStyle(alreadySelected ? "-fx-background-color: #09a30f;" : "");
                    }
                };
            }
        });
    }


    public void loadFuncionData(Funcion funcion) {
        try {

            cinemaName.getSelectionModel().select(funcion.getMovie().getName());
            salaName.getSelectionModel().select(funcion.getSalaId());

            isEditing = true;
            funcionForEdit = funcion;

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
