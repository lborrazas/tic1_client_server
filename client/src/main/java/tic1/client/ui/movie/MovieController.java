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

    private List<String> actors = new ArrayList<>();

    private List<String> genres = new ArrayList<>();

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
        List<String> actors = this.actors;
        List<String> genres = this.genres;

        if (isEditing) {

            if (name == null || name.equals("") || description == null || description.equals("") ||
                    duration == 0 || actors == null) {

                AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Datos faltantes!"
                        , "No se ingresaron los datos necesarios para completar el ingreso.");

            } else {

                movieForEdit.setName(txtName.getText());
                movieForEdit.setDescription(txtDescription.getText());
                movieForEdit.addActor(txtActors.getSelectionModel().getSelectedItem());
                movieForEdit.setDuration(Long.parseLong(txtDuration.getText()));
                movieForEdit.addGenre(txtGenre.getSelectionModel().getSelectedItem());

                showAlert("Pelicula actualizada", "Se actualizo con exito la pelicula!");

                movieRestTemplate.updateMovie(movieForEdit.getId(), movieForEdit);

                isEditing = false;

                close(event);
                principal.refreshTable();

            }
        } else {
            if (txtName.getText() == null || txtName.getText().equals("") ||
                    txtDescription.getText() == null || txtDescription.getText().equals("") ||
                    txtDuration.getText() == null || txtDuration.getText().equals("") ||
                    txtActors.getSelectionModel().getSelectedItem() == null ||
                    txtActors.getSelectionModel().getSelectedItem().equals("")) {

                AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Datos faltantes"
                        , "No se ingresaron los datos necesarios para completar el ingreso.");

            } else {

                try {

                    Movie movie = new Movie();

                    movie.setName(name);
                    movie.setDescription(description);
                    movie.setDuration(duration);
                    /*movie.setActors(actors);
                    movie.setGenre(genre);*/

                    showAlert("Pelicula agregada", "Se agrego con exito la pelicula!");

                    movieRestTemplate.createMovie(movie);

                    close(event);
                    principal.refreshTable();

                } catch (Exception e) {
                    System.out.println("Error");
                }
            }
        }
    }

    @FXML
    void clean() {

        txtName.setText(null);
        txtDescription.setText(null);
        txtDuration.setText(null);
//        txtActors.setText(null);

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
            /*txtActors.setText(movie.getActors());
            txtGenre.getSelectionModel().select(movie.getGenre());*/
            txtDuration.setText(Long.toString(movie.getDuration()));
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
        Callback<ListView<Actor>, ListCell<Actor>> factory1 = lv -> new ListCell<Actor>() {

            @Override
            protected void updateItem(Actor item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };
        Callback<ListView<Genre>, ListCell<Genre>> factory2 = lv -> new ListCell<Genre>() {

            @Override
            protected void updateItem(Genre item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getGenre());
            }

        };

        txtActors.setCellFactory(factory1);
        txtActors.setButtonCell(factory1.call(null));
        txtGenre.setCellFactory(factory2);
        txtGenre.setButtonCell(factory2.call(null));

        ActorRestTemplate actorRestTemplate = new ActorRestTemplate();
        GenreRestTemplate genreRestTemplate = new GenreRestTemplate();
        ObservableList<Actor> actors = FXCollections.observableArrayList(actorRestTemplate.findAll());
        ObservableList<Genre> genres = FXCollections.observableArrayList(genreRestTemplate.findAll());

        txtActors.setItems(actors);
        txtGenre.setItems(genres);
    }
}
