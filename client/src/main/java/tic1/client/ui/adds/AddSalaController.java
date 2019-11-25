package tic1.client.ui.adds;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Controller;
import tic1.client.models.Sala;
import tic1.client.models.Seat;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

//        sala.setSeats(seats);
        
    }

}
