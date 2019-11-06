package tic1.client.ui.movie;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.models.Actor;
import tic1.client.models.Genre;
import tic1.client.models.Movie;
import tic1.client.services.ActorRestTemplate;
import tic1.client.services.GenreRestTemplate;
import tic1.client.services.MovieRestTemplate;
import tic1.client.services.alert.AlertMaker;
import tic1.client.ui.Principal2;
import tic1.commons.transfers.MovieActorDTO;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Controller
public class MovieController implements Initializable {

    private Principal2 principal;

    @Autowired
    public MovieController(Principal2 principal) {
        this.principal = principal;
    }

    @Autowired
    private MovieRestTemplate movieRestTemplate;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnAdd;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXComboBox<Actor> txtActors;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private JFXComboBox<Genre> txtGenre;

    @FXML
    private JFXButton fileChooser;

    @FXML
    private StackPane rootPane;

    @FXML
    private TextField imageId;

    @FXML
    private VBox mainContainer;

    private boolean isEditing = false;

    private Movie movieForEdit;

    private Set<Actor> actors = new HashSet<>();

    private Set<Genre> genres = new HashSet<>();

    @FXML
    private TextArea actorsList;

    @FXML
    private TextArea genresList;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addMovie(ActionEvent event) {

        String name = txtName.getText();
        String description = txtDescription.getText();
        long duration = Long.parseLong(txtDuration.getText());
        HashSet<Actor> actors = (HashSet<Actor>) this.actors;
        HashSet<Genre> genres = (HashSet<Genre>) this.genres;

        if (isEditing) {

            if (name == null || name.equals("") || description == null || description.equals("") ||
                    duration == 0 || actors == null) {

                AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Datos faltantes!"
                        , "No se ingresaron los datos necesarios para completar el ingreso.");

            } else {

                movieForEdit.setName(txtName.getText());
                movieForEdit.setDescription(txtDescription.getText());
                movieForEdit.setDuration(Long.parseLong(txtDuration.getText()));
                movieForEdit.setActors(actors);
                movieForEdit.setGenre(genres);

                showAlert("Pelicula actualizada", "Se actualizo con exito la pelicula!");

                movieRestTemplate.updateMovie(movieForEdit.getId(), movieForEdit);

                this.actors.clear();
                this.genres.clear();

                isEditing = false;

                close(event);
                principal.refreshTable();

            }
        } else {
            if (txtName.getText() == null || txtName.getText().equals("") ||
                    txtDescription.getText() == null || txtDescription.getText().equals("") ||
                    txtDuration.getText() == null || txtDuration.getText().equals("") ||
                    this.actors == null || this.genres == null) {

                AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Datos faltantes"
                        , "No se ingresaron los datos necesarios para completar el ingreso.");

            } else {

                try {

                    Movie movie = new Movie();

                    movie.setName(name);
                    movie.setDescription(description);
                    movie.setDuration(duration);
                    movie.setActors(this.actors);
                    movie.setGenre(this.genres);

                    movieRestTemplate.createMovie(movie);

                    showAlert("Pelicula agregada", "Se agrego con exito la pelicula!");

                    this.actors.clear();
                    this.genres.clear();

                    close(event);
                    principal.refreshTable();

                } catch (Exception e) {
                    System.out.println("Error");
                }
            }
        }
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        AlertMaker.styleAlert(alert);
        alert.showAndWait();
    }

    public void loadMovieData(Movie movie) {
        try {

            txtName.setText(movie.getName());
            txtDescription.setText(movie.getDescription());
            StringBuilder actors = new StringBuilder();
            StringBuilder genres = new StringBuilder();
            for (Actor actor : movie.getActors()) {
                actors.append(actor.getName()).append(" ");
            }
            actorsList.setText(actors.toString());
            txtDuration.setText(Long.toString(movie.getDuration()));
            for (Genre genre : movie.getGenre()) {
                genres.append(genre.getGenre()).append(" ");
            }
            genresList.setText(genres.toString());
            this.actors.addAll(movie.getActors());
            this.genres.addAll(movie.getGenre());
            isEditing = true;
            movieForEdit = movie;

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chooseFile(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        String relativePath = null;

        if (selectedFile != null) {
            imageId.setText(selectedFile.getName());
        }
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Callback<ListView<Actor>, ListCell<Actor>> factory1 = lv1 -> new ListCell<Actor>() {

            @Override
            protected void updateItem(Actor item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };

        txtActors.setCellFactory(factory1);
        txtActors.setButtonCell(factory1.call(null));

        Callback<ListView<Genre>, ListCell<Genre>> factory2 = lv2 -> new ListCell<Genre>() {

            @Override
            protected void updateItem(Genre genre, boolean empty2) {
                super.updateItem(genre, empty2);
                setText(empty2 ? "" : genre.getGenre());
            }

        };

        txtGenre.setCellFactory(factory2);
        txtGenre.setButtonCell(factory2.call(null));

        ActorRestTemplate actorRestTemplate = new ActorRestTemplate();
        GenreRestTemplate genreRestTemplate = new GenreRestTemplate();
        ObservableList<Actor> actors = FXCollections.observableArrayList(actorRestTemplate.findAll());
        ObservableList<Genre> genres = FXCollections.observableArrayList(genreRestTemplate.findAll());
        txtActors.setItems(actors);
        txtGenre.setItems(genres);
        this.actors.clear();
        this.genres.clear();
    }

    @FXML
    void removeActor(ActionEvent event) {
        Actor actor = txtActors.getSelectionModel().getSelectedItem();
        if (actor != null) {
            actors.remove(actor);
            StringBuilder actors = new StringBuilder();
            for (Actor actor1 : this.actors) {
                actors.append(actor1.getName()).append(" ");
            }
            actorsList.setText("");
            actorsList.setText(actors.toString());
        }
    }

    @FXML
    void removeGenre(ActionEvent event) {
        Genre genre = txtGenre.getSelectionModel().getSelectedItem();
        if (genre != null) {
            genres.remove(genre);
            StringBuilder genres = new StringBuilder();
            for (Genre genre1 :  this.genres) {
                genres.append(genre1.getGenre()).append(" ");
            }
            genresList.setText(genres.toString());
        }
    }

    @FXML
    void addActor(ActionEvent event) {
        Actor actor = txtActors.getSelectionModel().getSelectedItem();
        if (actor != null) {
            actors.add(actor);
            StringBuilder actors = new StringBuilder();
            for (Actor actor1 : this.actors) {
                actors.append(actor1.getName()).append(" ");
            }
            actorsList.setText("");
            actorsList.setText(actors.toString());
        }
    }

    @FXML
    void addGenre(ActionEvent event) {
        Genre genre = txtGenre.getSelectionModel().getSelectedItem();
        if (genre != null) {
            genres.add(genre);

            StringBuilder genres = new StringBuilder();
            for (Genre genre1 : this.genres) {
                genres.append(genre1.getGenre()).append(" ");
            }
            genresList.setText(genres.toString());
//            movieForEdit.addGenre(genre);
        }
    }
}
