package tic1.client.ui.client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.Actor;
import tic1.client.models.Genre;
import tic1.client.models.Movie;
import tic1.client.services.ActorRestTemplate;
import tic1.client.services.GenreRestTemplate;
import tic1.client.services.MovieRestTemplate;
import tic1.client.ui.movie.MovieListItemController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class MovieListController implements Initializable {

    @FXML
    private AnchorPane rootContainer;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private ScrollPane pane;

    @FXML
    private VBox list;

    @FXML
    private JFXComboBox<Genre> genreFilter;

    @FXML
    private JFXComboBox<Actor> actorFilter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Callback<ListView<Actor>, ListCell<Actor>> factory1 = lv1 -> new ListCell<Actor>() {

            @Override
            protected void updateItem(Actor item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };

        actorFilter.setCellFactory(factory1);
        actorFilter.setButtonCell(factory1.call(null));

        Callback<ListView<Genre>, ListCell<Genre>> factory2 = lv2 -> new ListCell<Genre>() {

            @Override
            protected void updateItem(Genre genre, boolean empty2) {
                super.updateItem(genre, empty2);
                setText(empty2 ? "" : genre.getGenre());
            }

        };

        genreFilter.setCellFactory(factory2);
        genreFilter.setButtonCell(factory2.call(null));

        GenreRestTemplate genreRestTemplate = new GenreRestTemplate();
        ActorRestTemplate actorRestTemplate = new ActorRestTemplate();
        MovieRestTemplate movieRestTemplate = new MovieRestTemplate();
        List<Movie> movies = movieRestTemplate.findAllPaged(0);
        boolean evenRow = false;
        for (Movie movie : movies) {
            try {
                if (evenRow) {
                    addMovie(movie, true);
                    evenRow = false;
                } else {
                    addMovie(movie, false);
                    evenRow = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        actorFilter.setItems(FXCollections.observableArrayList(actorRestTemplate.findAll()));
        genreFilter.setItems(FXCollections.observableArrayList(genreRestTemplate.findAll()));
    }

    @FXML
    private void addMovie(Movie movie, boolean evenRow) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(MovieListItemController.class.getResourceAsStream("/movie_crud/ui/movie/MovieListItem.fxml"));
        MovieListItemController movieListItemController = fxmlLoader.getController();
        movieListItemController.setEvenRow(evenRow);
        movieListItemController.populate(movie);
        list.getChildren().add(root);
    }

    @FXML
    public void goToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(EndUserController.class.getResourceAsStream("/movie_crud/ui/client/EndUser.fxml"));
    }

    @FXML
    public void filter() {
        list.getChildren().clear();

        Actor actor = actorFilter.getSelectionModel().getSelectedItem();
        Genre genre = genreFilter.getSelectionModel().getSelectedItem();

        List<Movie> filteredList = new ArrayList<>();

        MovieRestTemplate movieRestTemplate = new MovieRestTemplate();

        if (actor != null) {
            filteredList = movieRestTemplate.filterActorPaged(actor, 0);
            System.out.println();
        }

        if (genre != null) {
            movieRestTemplate.filterGenrePaged(genre, 0);
        }

        boolean evenRow = false;

        if (filteredList.isEmpty()) {

            Label label = new Label();
            label.setText("No se encontraron resultados.");



        }

        for (Movie movie : filteredList) {
            try {
                if (evenRow) {
                    addMovie(movie, true);
                    evenRow = false;
                } else {
                    addMovie(movie, false);
                    evenRow = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}