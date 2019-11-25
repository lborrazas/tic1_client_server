package tic1.client.ui.adds;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.css.PseudoClass;
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
import tic1.client.models.User;
import tic1.client.services.SalaRestTemplate;

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

    /*private final ObservableSet<Button> selectedLabels = FXCollections.observableSet();


    private final int ROWS = 10 ;
    private final int COLS = 16 ;

    private final Button[][] buttons = new Button[COLS][ROWS];

    private final PseudoClass SELECTED = PseudoClass.getPseudoClass("selected");

    @Override
    public void start(Stage primaryStage) {
        GridLocation mouseDownLoc = new GridLocation();
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setGridLinesVisible(true);

        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                addLabel(grid, i, j, mouseDownLoc);
            }
        }

        selectedLabels.addListener((Change<? extends Label> change) -> {
            if (change.wasAdded()) {
                Label label = change.getElementAdded();
                label.pseudoClassStateChanged(SELECTED, true);
            } else if (change.wasRemoved()) {
                Label label = change.getElementRemoved();
                label.pseudoClassStateChanged(SELECTED, false);
            }
        });

        Scene scene = new Scene(grid, 800, 800);
        scene.getStylesheets().add("dragging-grid-pane.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addLabel(GridPane grid, int col, int row,
                          GridLocation mouseDownLoc) {
        Label label = new Label("Cell ["+(col+1)+", "+(row+1)+"]");
        labels[col][row] = label ;

        grid.add(label, col, row);
        GridPane.setFillWidth(label, true);
        GridPane.setHgrow(label, Priority.ALWAYS);
        GridPane.setVgrow(label, Priority.ALWAYS);

        label.setAlignment(Pos.CENTER);
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


        label.setOnDragDetected(event -> {
            mouseDownLoc.x = col ;
            mouseDownLoc.y = row ;
            selectedLabels.clear();
            selectedLabels.add(label);
            label.startFullDrag();
        });

        label.setOnMouseDragEntered(event ->
                recomputeSelection(mouseDownLoc, col, row));

    }

    private void recomputeSelection(GridLocation mouseDown,
                                    int x, int y) {
        Set<Label> newSelection = new HashSet<>();
        int startX = Math.min(x,  mouseDown.x);
        int endX = Math.max(x, mouseDown.x);
        int startY = Math.min(y, mouseDown.y);
        int endY = Math.max(y, mouseDown.y);

        for (int j = startY; j <= endY; j++) {
            for (int i = startX; i <= endX; i++) {
                newSelection.add(labels[i][j]);
            }
        }

        // remove anything in selectedLabels
        // that is not in newSelection:
        selectedLabels.retainAll(newSelection);

        // add everything from newSelection
        // to selectedLabels (will not duplicate):
        selectedLabels.addAll(newSelection);
    }

    private static class GridLocation {
        int x, y ;
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/

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
