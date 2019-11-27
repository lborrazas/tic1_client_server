package tic1.client.ui.movie;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.Cinema;
import tic1.client.models.Ticket;
import tic1.client.services.CinemaRestTemplate;
import tic1.client.ui.client.EndUserController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class Subtotal implements Initializable {

    @Autowired
    private CinemaRestTemplate cinemaRestTemplate;

    @FXML
    private Label cantidadComprados;

    @FXML
    private Label totalPrice;

    @FXML
    private Label movieName;

    @FXML
    private Label cinemaName;

    @FXML
    private Label cinemaLocation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       /* for (:) {
            populateSala();
        }*/
    }

    public void loadBuyInfo(List<Ticket> tickets) {

        Cinema cinema = cinemaRestTemplate.getOne(tickets.get(0).getFuncion().getCinemaId());
        cantidadComprados.setText(String.valueOf(tickets.size()));
        movieName.setText(tickets.get(0).getFuncion().getMovie().getName());
        cinemaName.setText(cinema.getName());
        cinemaLocation.setText(cinema.getLocation());
        totalPrice.setText(String.valueOf(500));

    }

    @FXML
    void goToMain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = null;
        root = fxmlLoader.load(EndUserController.class.getResourceAsStream("/movie_crud/ui/client/EndUser.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/movie_crud/ui/styles/dark-theme.css");
        stage.setScene(scene);
        stage.show();
        close(event);
    }

    @FXML
    private void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
