package com.example.movie_crud.ui;

import com.example.movie_crud.MovieCrudApplication;
import com.example.movie_crud.business.MovieMgr;
import com.example.movie_crud.business.entities.Movie;
import com.example.movie_crud.ui.movie.MovieController;
import com.example.movie_crud.ui.movie.MovieDetailsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class Principal implements Initializable {

    @Autowired
    private MovieMgr movieMgr;

    @FXML
    private TableView<Movie> movieTable;

    @FXML
    public TableColumn<Movie, String> colName;

    @FXML
    public TableColumn<Movie, String> colDescription;

    @FXML
    public TableColumn<Movie, String> colActors;

    @FXML
    public TableColumn<Movie, String> colDuration;

    @FXML
    public TableColumn<Movie, Boolean> colEdit;

    @FXML
    private MenuItem deleteUsers;

    private ObservableList<Movie> movieList = FXCollections.observableArrayList();

    @FXML
    void addMovie(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(MovieController.class.getResourceAsStream("AddMovie.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void loadMovies() {
        movieList.clear();
        movieList.addAll(movieMgr.findAll());

        movieTable.setItems(movieList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        movieTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colActors.setCellValueFactory(new PropertyValueFactory<>("actors"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colEdit.setCellFactory(cellFactory);
        //add your data to the table here.
        loadMovies();
    }

    Callback<TableColumn<Movie, Boolean>, TableCell<Movie, Boolean>> cellFactory =
            new Callback<TableColumn<Movie, Boolean>, TableCell<Movie, Boolean>>() {
                @Override
                public TableCell<Movie, Boolean> call(final TableColumn<Movie, Boolean> param) {
                    final TableCell<Movie, Boolean> cell = new TableCell<Movie, Boolean>() {
//                        Image imgEdit = new Image(getClass().getResourceAsStream("../images/edit.png"));
                        final Button btnEdit = new Button();

                        @Override
                        public void updateItem(Boolean check, boolean empty) {
                            super.updateItem(check, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btnEdit.setOnAction(e -> {
                                    try {
                                        Movie movie = getTableView().getItems().get(getIndex());
                                        updateMovie(movie);
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                });
                                btnEdit.setStyle("-fx-background-color: transparent;");
                                /*ImageView iv = new ImageView();
                                iv.setImage(imgEdit);
                                iv.setPreserveRatio(true);
                                iv.setSmooth(true);
                                iv.setCache(true);
                                btnEdit.setGraphic(iv);*/

                                setGraphic(btnEdit);
                                setAlignment(Pos.CENTER);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };

    private void updateMovie(Movie movie) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(MovieController.class.getResourceAsStream("AddMovie.fxml"));

        MovieController movieController = fxmlLoader.getController();

        movieController.loadMovieData(movie);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/style.css");

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void refreshTable() {
        movieList.clear();
        movieList.addAll(movieMgr.findAll());

        movieTable.setItems(movieList);

    }

    private void loadMovieDetails() {
        try {
            //Load second scene
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

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
        fxmlLoader.setControllerFactory(MovieCrudApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(MovieDetailsController.class.getResourceAsStream("MovieDetails.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
