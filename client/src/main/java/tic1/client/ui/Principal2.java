package tic1.client.ui;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tic1.client.ClientApplication;
import tic1.client.models.Genre;
import tic1.client.models.Movie;
import tic1.client.services.MovieRestTemplate;
import tic1.client.services.alert.AlertMaker;
import tic1.client.ui.adds.AddAdminController;
import tic1.client.ui.adds.AddCinemaController;
import tic1.client.ui.adds.AddProviderController;
import tic1.client.ui.adds.AddSalaController;
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
import java.util.Set;
import java.util.stream.Collectors;

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
    public TableColumn<Movie, Set<Genre>> colGenre;

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

    @FXML
    private JFXTextField filter;

    @Autowired
    public Principal2(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }


    private ObservableList<Movie> movieList = FXCollections.observableArrayList();

    @FXML
    void addMovie(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(MovieController.class.getResourceAsStream("/movie_crud/ui/movie/AddMovie.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/movie_crud/ui/styles/dark-theme.css");
        stage.setScene(scene);
        stage.show();
    }

    private void loadMovies() {
        movieList.clear();
        movieList.addAll(movieRestTemplate.findAllPaged(0));

        movieTable.setItems(movieList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        movieTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colGenre.setCellFactory(col -> new TableCell<Movie, Set<Genre>>() {
            @Override
            public void updateItem(Set<Genre> genres, boolean empty) {
                super.updateItem(genres, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(genres.stream().map(Genre::getGenre)
                            .collect(Collectors.joining(", ")));
                }
            }
        });
        loadMovies();

        FilteredList<Movie> filteredData = new FilteredList<>(movieList, e -> true);

        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(movie -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (movie.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } /*else if () {

            }*/
                return false;
            });
        });
        SortedList<Movie> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(movieTable.comparatorProperty());
        movieTable.setItems(sortedData);
    }

    public void refreshTable() {
        movieList.clear();
        movieList.addAll(movieRestTemplate.findAllPaged(0));

        movieTable.setItems(movieList);

    }


    private void loadMovieDetails() {
        try {
            //Load second scene
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

            Parent root = fxmlLoader.load(MovieDetailsController.class.getResourceAsStream("/movie_crud/ui/movie/MovieDetails.fxml"));

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

    public void viewDetails(ActionEvent event) throws IOException {
        Movie selectedForPreview = movieTable.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(MovieDetailsController.class.getResourceAsStream("/movie_crud/ui/movie/MovieDetails.fxml"));

        MovieDetailsController movieDetailsController = fxmlLoader.getController();
        movieDetailsController.loadData(selectedForPreview);
        movieDetailsController.setParent("Principal2");

        Scene scene = new Scene(root,800,600);
        Stage stage =  (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteMovie(ActionEvent event) {
        //Fetch the selected row
       Movie selectedForDeletion = movieTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Eliminando Pelicula...");
        alert.setContentText("Seguro quieres eliminar " + selectedForDeletion.getName() + " de manera permanente?");
        AlertMaker.styleAlert(alert);
        Optional<ButtonType> answer = alert.showAndWait();

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        if (answer.get() == ButtonType.OK) {
            movieRestTemplate.deleteMovie(selectedForDeletion.getId());
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

            Parent root = fxmlLoader.load(MovieController.class.getResourceAsStream("/movie_crud/ui/movie/AddMovie.fxml"));

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

    @FXML
    void addAdmin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(AddAdminController.class.getResourceAsStream("/movie_crud/ui/adds/AddAdmin.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void createCinema(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(AddCinemaController.class.getResourceAsStream("/movie_crud/ui/adds/AddCinema.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void createProvider(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(AddProviderController.class.getResourceAsStream("/movie_crud/ui/adds/AddProvider.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    private void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
