package tic1.client.ui.movie;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.Funcion;
import tic1.client.models.Sala;
import tic1.client.models.Seat;
import tic1.client.models.Ticket;
import tic1.client.services.FuncionRestTemplate;
import tic1.client.services.TransaccionRestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class BuyTicketController implements Initializable {

    @Autowired
    private TransaccionRestTemplate transaccionRestTemplate;

    @Autowired
    private FuncionRestTemplate funcionRestTemplate;

    @FXML
    private GridPane grid;

    private List<Ticket> tickets = new ArrayList<>();
    private List<Ticket> ticketsSelected = new ArrayList<>();

    @FXML
    private AnchorPane rootContainer;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tickets.clear();
        ticketsSelected.clear();
        ObservableList<Node> seats = grid.getChildren();
        for (Node seat : seats) {
            System.out.println("Asiento");
            seat.setDisable(true);
        }

    }

    public void loadSala(List<Ticket> tickets) {
        this.tickets = tickets;
        for (Ticket ticket : tickets) {
            Node seat = getNodeByRowColumnIndex(ticket.getSeat().getFila(), ticket.getSeat().getColumna());
            seat.setDisable(false);
            if (ticket.isBought()) {
                seat.getStyleClass().clear();
                seat.getStyleClass().add("seatsOccupied");
            } else {
                seat.getStyleClass().clear();
                seat.getStyleClass().add("seatsAvailable");
            }
        }

    }

    @FXML
    public void buySeat(ActionEvent event) {

        Button buttonPressed = (Button) event.getSource();

        int row = GridPane.getRowIndex(buttonPressed);

        int column = GridPane.getColumnIndex(buttonPressed);

        Node seat = getNodeByRowColumnIndex(row, column);
        Ticket ticket = find(row, column);
        if (!seat.getStyleClass().contains("seatsSelected")) {
            seat.getStyleClass().clear();
            seat.getStyleClass().add("seatsSelected");
            ticketsSelected.add(ticket);
        } else {
            seat.getStyleClass().clear();
            seat.getStyleClass().add("seatsAvailable");
            ticketsSelected.remove(ticket);
        }
    }

    @FXML
    private void buyAction(ActionEvent event) {

    }

    @FXML
    public void goToDetails(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(MovieDetailsController.class.getResourceAsStream("/movie_crud/ui/movie/MovieDetails.fxml"));
        MovieDetailsController movieDetailsController = fxmlLoader.getController();
        List<Funcion> funciones = funcionRestTemplate.getByMovieId(movieDetailsController.getMovieDetails());
        movieDetailsController.loadData(movieDetailsController.getMovieDetails(), funciones);
        Scene scene = backButton.getScene();
        root.translateXProperty().set(-scene.getWidth());

        rootContainer.getChildren().add(root);

        Timeline timeline1 = new Timeline();
        KeyValue kv1 = new KeyValue(this.root.translateXProperty(), (2 * scene.getWidth()), Interpolator.EASE_OUT);
        KeyFrame kf1 = new KeyFrame(Duration.seconds(0.7), kv1);
        timeline1.getKeyFrames().add(kf1);
        timeline1.setOnFinished(t -> {
            rootContainer.getChildren().remove(this.root);
        });

        Timeline timeline2 = new Timeline();
        KeyValue kv2 = new KeyValue(rootContainer.translateXProperty(), scene.getWidth(), Interpolator.EASE_IN);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(0.7), kv2);
        timeline2.getKeyFrames().add(kf2);
        timeline1.play();
        timeline2.play();
    }

    private Node getNodeByRowColumnIndex (final long row, final long column) {
        Node result = null;
        ObservableList<Node> childrens = grid.getChildren();

        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    private Ticket find(long row, long column) {
        Seat seat;
        for (Ticket ticket : tickets) {
             seat = ticket.getSeat();
            if (seat.getFila() == row && seat.getColumna() == column) return ticket;
        }
        return null;
    }
}
