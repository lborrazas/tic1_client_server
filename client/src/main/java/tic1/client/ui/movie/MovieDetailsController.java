package tic1.client.ui.movie;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.models.Movie;
import tic1.client.services.MovieRestTemplate;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MovieDetailsController implements Initializable {

    @Autowired
    MovieRestTemplate movieRestTemplate;

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

    @FXML
    void mouseEntered(MouseEvent event) {
        buy_btn.setEffect(new DropShadow());

        buy_btn.addEventHandler(MouseEvent.MOUSE_EXITED, event1 -> buy_btn.setEffect(null));
    }
}
