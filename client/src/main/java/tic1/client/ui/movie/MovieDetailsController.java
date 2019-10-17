package tic1.client.ui.movie;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.Movie;
import tic1.client.services.MovieRestTemplate;
import tic1.client.ui.Principal2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MovieDetailsController implements Initializable {

    @Autowired
    MovieRestTemplate movieRestTemplate;

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

    public void buyAction(ActionEvent event) {

    }

    public void goToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(Principal2.class.getResourceAsStream("Principal2.fxml"));
        Scene scene = new Scene(root,800,500);
        Stage stage =  (Stage) ((JFXButton) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
