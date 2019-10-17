package tic1.client.ui;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tic1.client.ClientApplication;
import tic1.client.models.Movie;
import tic1.client.services.MovieRestTemplate;
import tic1.client.ui.movie.MovieController;
import tic1.client.ui.movie.MovieDetailsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class Principal2 implements Initializable {

    @Autowired
    private MovieRestTemplate movieRestTemplate; //todo all movimgr references to controller

    @FXML
    private TableView<Movie> movieTable;

    @FXML
    public TableColumn<Movie, String> colName;

    @FXML
    public TableColumn<Movie, String> colDuration;

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private Tab movieTab;

    @FXML
    private MenuItem edit;

    @FXML
    private MenuItem delete;

    private ClientApplication clientApplication;

    @Autowired
    public Principal2(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }


    private ObservableList<Movie> movieList = FXCollections.observableArrayList();
    private ObservableList<String> genres = FXCollections.observableArrayList("Accion", "Drama", "Suspenso");

    @FXML
    void addMovie(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(MovieController.class.getResourceAsStream("AddMovie.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/movie_crud/ui/styles/dark-theme.css");
        stage.setScene(scene);
        stage.show();
    }

    private void loadMovies() {
        movieList.clear();
        movieList.addAll((Movie) movieRestTemplate.findAll());  //todo Stop casting

        movieTable.setItems(movieList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        movieTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        loadMovies();
    }

    public void refreshTable() {
        movieList.clear();
        movieList.addAll((Movie) movieRestTemplate.findAll()); //todo Stop Casting

        movieTable.setItems(movieList);

    }


    private void loadMovieDetails() {
        try {
            //Load second scene
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

            Parent root = fxmlLoader.load(MovieDetailsController.class.getResourceAsStream("MovieDetails.fxml"));

            //Get controller of scene2
            MovieDetailsController movieDetailsController = fxmlLoader.getController();
            //Pass whatever data you want. You can have multiple method calls here
//            movieDetailsController.loadData();

            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void viewDetails() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(MovieDetailsController.class.getResourceAsStream("MovieDetails.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteMovie(ActionEvent event) {
        //Fetch the selected row
       Movie selectedForDeletion = movieTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting book");
        alert.setContentText("Are you sure want to delete the book " + selectedForDeletion.getName() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        if (answer.get() == ButtonType.OK) {
            movieRestTemplate.deleteMovie(1);
           // movieRestTemplate.deleteMovie(selectedForDeletion.getId()); todo id to movie
            alert1.setContentText("Pelicula " +  selectedForDeletion.getName() + " borrada con exito.");
            movieList.remove(selectedForDeletion);

        } else {
            alert1.setContentText("Borrado cancelado.");
        }
    }

    @FXML
    public void editMovie(ActionEvent event) {
        //Fetch the selected row
       Movie selectedForEdit = movieTable.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

            Parent root = fxmlLoader.load(MovieController.class.getResourceAsStream("AddMovie.fxml"));

            //Get controller of scene2
            MovieController movieController = fxmlLoader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            movieController.loadMovieData(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Editar Pelicula");
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/movie_crud/ui/styles/dark-theme.css");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
