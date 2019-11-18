package tic1.client.ui.movie;

import com.sun.prism.paint.Color;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import org.springframework.stereotype.Controller;
import tic1.client.models.Actor;
import tic1.client.models.Genre;
import tic1.client.models.Movie;
import tic1.client.services.alert.ImageRestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class MovieListItemController implements Initializable {


    @FXML
    public ImageView movie_image;

    @FXML
    private Label movieName;

    @FXML
    private Label movieDuration;

    @FXML
    private Text movieDescription;

    @FXML
    private Text movieActors;

    @FXML
    private Text movieGenres;

    @FXML
    private AnchorPane root;

    private boolean evenRow = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void populate(Movie movie) {
        if (evenRow) root.getStyleClass().add("dark-row");
        movieName.setText(movie.getName());
        movieDuration.setText(Long.toString(movie.getDuration()));
        movieDescription.setText(movie.getDescription());
        movieActors.setText(movie.getActors().stream().map(Actor::getName)
                .collect(Collectors.joining(", ")));
        movieGenres.setText(movie.getGenre().stream().map(Genre::getGenre)
                .collect(Collectors.joining(", ")));
        ImageRestTemplate imageRestTemplate = new ImageRestTemplate();
        try {
            movie_image.setImage(imageRestTemplate.showImage(movie.getImagePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void buyAction(ActionEvent event) {

    }


    public AnchorPane getRoot() {
        return root;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }

    public boolean isEvenRow() {
        return evenRow;
    }

    public void setEvenRow(boolean evenRow) {
        this.evenRow = evenRow;
    }
}
