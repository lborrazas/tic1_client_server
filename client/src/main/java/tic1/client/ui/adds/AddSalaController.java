package tic1.client.ui.adds;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import tic1.client.models.Sala;
import tic1.client.models.Seat;
import tic1.client.models.User;
import tic1.client.services.SalaRestTemplate;
import javafx.collections.SetChangeListener.Change;

import java.net.URL;
import java.util.*;

@Controller
public class AddSalaController implements Initializable {

    @FXML
    private GridPane grid;

    private List<Seat> seats = new ArrayList<>();

    private int[][] seatsSelected = new int[10][16];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        seats.clear();
        for (int[] i : seatsSelected) {
            for (int j : i) {
                i[j] = 0;
            }
        }
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

        sala.setCinemaId(11);

        sala.setName("Hola");

        sala.setSeats(seats);

        SalaRestTemplate salaRestTemplate = new SalaRestTemplate();

        salaRestTemplate.createSala(sala);

    }

}
