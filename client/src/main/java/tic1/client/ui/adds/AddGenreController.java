package tic1.client.ui.adds;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Controller;
import tic1.client.models.Genre;
import tic1.client.services.GenreRestTemplate;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AddGenreController implements Initializable {

    @FXML
    private JFXTextField genreName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void addGenre(ActionEvent event) {

        GenreRestTemplate genreRestTemplate = new GenreRestTemplate();

        Genre genre = new Genre();

        genre.setGenre(genreName.getText());

        genreRestTemplate.createGenre(genre);

    }
}
