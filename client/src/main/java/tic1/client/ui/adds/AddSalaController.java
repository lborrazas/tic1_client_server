package tic1.client.ui.adds;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.*;
import tic1.client.services.CinemaRestTemplate;
import tic1.client.services.SalaRestTemplate;

import java.net.URL;
import java.util.*;

@Controller
public class AddSalaController implements Initializable {

    private UserManager userManager;

    @Autowired
    private CinemaRestTemplate cinemaRestTemplate;

    @FXML
    private GridPane grid;

    @FXML
    private JFXTextField salaName;

    @FXML
    private JFXComboBox<Cinema> cinemas;

    private List<Seat> seats = new ArrayList<>();

    private int[][] seatsSelected = new int[10][16];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userManager = ClientApplication.userManager;
        seats.clear();
        for (int[] i : seatsSelected) {
            for (int j : i) {
                i[j] = 0;
            }
        }
        ObservableList<Cinema> cinemas =
                FXCollections.observableArrayList(cinemaRestTemplate.getByProvider(userManager.getProvider()));

        this.cinemas.setItems(cinemas);

        Callback<ListView<Cinema>, ListCell<Cinema>> factory1 = lv1 -> new ListCell<Cinema>() {

            @Override
            protected void updateItem(Cinema item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };

        this.cinemas.setCellFactory(factory1);
        this.cinemas.setButtonCell(factory1.call(null));
    }

    @FXML
    private void createSeat(ActionEvent event) {

        Button buttonPressed = (Button) event.getSource();

        int row = GridPane.getRowIndex(buttonPressed);

        int column = GridPane.getColumnIndex(buttonPressed);

        Seat seat = new Seat();

        seat.setFila(row);

        seat.setColumna(column);


        if (seatsSelected[row][column] == 0) {
            seats.add(seat);
            buttonPressed.getStyleClass().add("buttonSelected");
            seatsSelected[row][column] = 1;

        } else {
            seats.remove(seat);
            seatsSelected[row][column] = 0;
            buttonPressed.getStyleClass().clear();
            buttonPressed.getStyleClass().add("button");
        }
    }

    @FXML
    private void createSala(ActionEvent event) {

        Sala sala = new Sala();

        sala.setCinemaId(cinemas.getSelectionModel().getSelectedItem().getId());

        sala.setName(salaName.getText());

        sala.setSeats(seats);

        SalaRestTemplate salaRestTemplate = new SalaRestTemplate();

        salaRestTemplate.createSala(sala);

        close(event);

    }

    @FXML
    private void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
