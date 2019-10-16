package tic1.client.ui.movie;


import tic1.client.models.Movie;
import tic1.client.services.MovieRestTemplate;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ui.Principal2;

@Controller
public class MovieController {

    Principal2 principal;

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
    private JFXTextField txtActors;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private JFXComboBox<String> txtGenre;

    private ObservableList<String> genres = FXCollections.observableArrayList("Accion", "Drama", "Suspenso");


    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addMovie(ActionEvent event) {
        if (txtName.getText() == null || txtName.getText().equals("") ||
                txtDescription.getText() == null || txtDescription.getText().equals("") ||
                txtDuration.getText() == null || txtDuration.getText().equals("") ||
                txtActors.getText() == null || txtActors.getText().equals("")) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            try {

                String name = txtName.getText();
                String description = txtDescription.getText();
                String duration = txtDuration.getText();
                String actors = txtActors.getText();

                try {
                    movieRestTemplate.createMovie(description, duration, name, actors);
                    showAlert("Pelicula agregada", "Se agrego con exito la pelicula!");
                    close(event);
                    principal.refreshTable();
                } catch (Exception e) {
                    System.out.println("Error");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void clean() {

        txtName.setText(null);
        txtDescription.setText(null);
        txtDuration.setText(null);
        txtActors.setText(null);

    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    public void loadMovieData(Movie movie) {
        try {

            txtName.setText(movie.getName());
            txtDescription.setText(movie.getDescription());
            txtActors.setText(movie.getActors());
            txtDuration.setText(movie.getDuration());
            txtGenre.setItems(genres);


        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
