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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class SeatSelectionController implements Initializable {

    @FXML
    private AnchorPane rootContainer;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton backButton;

    @FXML
    private GridPane grid;

    @FXML
    public void goToDetails(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(MovieDetailsController.class.getResourceAsStream("/movie_crud/ui/movie/MovieDetails.fxml"));
        MovieDetailsController movieDetailsController = fxmlLoader.getController();
//        movieDetailsController.loadData(movieDetailsController.getMovieDetails() );
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

    /*public void populateSala(Seat seat) {

        grid.add(seat, seat.getRow, seat.getColumn);
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       /* for (:) {
            populateSala();
        }*/
    }
}
