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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
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
import java.util.LinkedHashSet;
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

    @FXML
    private JFXButton deleteFilterButton;

    @FXML
    private Text filters;

    private Label noMatches = new Label("No se encontraron resultados.");

    private ArrayList<String> filtersList = new ArrayList<>();

    private ArrayList<Movie> filteredList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteFilterButton.setVisible(false);
        noMatches.setVisible(false);
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
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((JFXButton) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void filter() {
        list.getChildren().clear();
        filteredList.clear();

        Actor actor = actorFilter.getSelectionModel().getSelectedItem();
        Genre genre = genreFilter.getSelectionModel().getSelectedItem();

        MovieRestTemplate movieRestTemplate = new MovieRestTemplate();

        if (actor != null) {
            filteredList.addAll(movieRestTemplate.filterActorPaged(actor, 0));
            filtersList.add(actor.getName());
        }

        if (genre != null) {

            List<Movie> filter = movieRestTemplate.filterGenrePaged(genre, 0);
            filtersList.add(genre.getGenre());
            for (Movie movie : filter) {
                if (!filteredList.contains(movie)) filteredList.add(movie);
            }
        }


        if (genre == null && actor == null) filteredList.addAll(movieRestTemplate.findAllPaged(0));

        boolean evenRow = false;

        if (filteredList.isEmpty()) {
            noMatches.setVisible(true);
            list.setAlignment(Pos.TOP_CENTER);
            list.getChildren().add(noMatches);
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

        filters.setText(String.join(", ", filtersList));

        actorFilter.getSelectionModel().clearSelection();
        genreFilter.getSelectionModel().clearSelection();
        deleteFilterButton.setVisible(true);
    }

    @FXML
    private void removeFilter(ActionEvent event) {
        list.getChildren().clear();
        filtersList.clear();
        actorFilter.getSelectionModel().clearSelection();
        genreFilter.getSelectionModel().clearSelection();
        MovieRestTemplate movieRestTemplate = new MovieRestTemplate();
        filteredList.clear();
        filteredList.addAll(movieRestTemplate.findAllPaged(0));
        boolean evenRow = false;
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
        deleteFilterButton.setVisible(false);
        noMatches.setVisible(false);
        filters.setText(null);
    }
}