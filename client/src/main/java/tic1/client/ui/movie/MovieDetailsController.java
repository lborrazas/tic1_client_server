package tic1.client.ui.movie;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.Movie;
import tic1.client.services.MovieRestTemplate;
import tic1.client.ui.Principal2;
import tic1.client.ui.client.EndUserController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MovieDetailsController implements Initializable {

    @Autowired
    private MovieRestTemplate movieRestTemplate;

    private ClientApplication clientApplication;

    @Autowired
    public MovieDetailsController(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }

    @FXML
    private Text movie_name;

    @FXML
    private Text movie_duration;

    @FXML
    private Label movie_description;

    @FXML
    private Label movie_actors;

    @FXML
    private Button buy_btn;

    @FXML
    private JFXComboBox<?> movie_date;

    @FXML
    private JFXComboBox<?> movie_time;

    @FXML
    private JFXButton minus_button;

    @FXML
    private TextField movie_quantity;

    @FXML
    private AnchorPane rootContainer;

    @FXML
    private AnchorPane anchorRoot;

    private int numberOfEntrances = 0;

    @FXML
    public void loadData(Movie movie) {

        movie_name.setText(movie.getName());
        movie_description.setText(movie.getDescription());
        movie_actors.setText(movie.getActors());
        movie_duration.setText(movie.getDuration());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        loadData();
    }

    public void buyAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(SeatSelectionController.class.getResourceAsStream("/movie_crud/ui/movie/SeatSelection.fxml"));
        Scene scene = buy_btn.getScene();
        root.translateXProperty().set(scene.getWidth());

        rootContainer.getChildren().add(root);

        Timeline timeline1 = new Timeline();
        KeyValue kv1 = new KeyValue(anchorRoot.translateXProperty(), -scene.getWidth(), Interpolator.EASE_OUT);
        KeyFrame kf1 = new KeyFrame(Duration.seconds(1), kv1);
        timeline1.getKeyFrames().add(kf1);
        timeline1.setOnFinished(t -> {
            rootContainer.getChildren().remove(anchorRoot);
        });

        Timeline timeline2 = new Timeline();
        KeyValue kv2 = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(1), kv2);
        timeline2.getKeyFrames().add(kf2);
        timeline1.play();
        timeline2.play();
    }

    public void sum() {
        if (numberOfEntrances < 20) numberOfEntrances++;
        movie_quantity.setText(String.valueOf(numberOfEntrances));
    }

    public void minus() {
        if (numberOfEntrances > 0) numberOfEntrances--;
        movie_quantity.setText(String.valueOf(numberOfEntrances));
    }

    public void goToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        if (actionEvent.getSource() instanceof MenuItem) {
            Parent root = fxmlLoader.load(Principal2.class.getResourceAsStream("/movie_crud/ui/Principal2.fxml"));
            Scene scene = new Scene(root, 800, 500);
            Stage stage = (Stage) ((JFXButton) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            Parent root = fxmlLoader.load(EndUserController.class.getResourceAsStream("/movie_crud/ui/client/EndUser.fxml"));
            EndUserController euc = fxmlLoader.getController();
            euc.goBack();
        }

    }
}
