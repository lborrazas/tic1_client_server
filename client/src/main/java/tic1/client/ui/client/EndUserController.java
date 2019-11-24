package tic1.client.ui.client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDrawerEvent;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.Movie;
import tic1.client.services.MovieRestTemplate;
import tic1.client.ui.login.LoginController;
import tic1.client.ui.movie.MovieDetailsController;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

@Controller
public class EndUserController implements Initializable {

    @Autowired
    private MovieRestTemplate movieMgr;

    @FXML
    private AnchorPane rootContainer;

    @FXML
    private AnchorPane mainContent;

    @FXML
    public VBox toolbarContent;

    private Parent movieDetails;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXDrawer header;

    @FXML
    private HBox box;

    @FXML
    private AnchorPane homeContent;

    @FXML
    private GridPane grid;
    private int rows = 4;
    private int columns = 8;

    @FXML
    private ScrollPane pane;

    @FXML
    private ImageView pic;

    @FXML
    private Image image;

    @FXML
    String id;

//    private ArrayList<File> fileList = new ArrayList<>();

    private HBox hb = new HBox();

    @FXML
    private JFXButton homeButton;

    @FXML
    private JFXButton moviesButton;

    @FXML
    private JFXButton theatreButton;

    @FXML
    private JFXButton settingsButton;

    @FXML
    private JFXButton logOutButton;

    private HamburgerBackArrowBasicTransition transition;

    @FXML
    private JFXTextField filterByName;

    @FXML
    private JFXButton filterButton;

    @FXML
    private JFXButton deleteFilterButton;

    private ObservableList<File> fileList = FXCollections.observableArrayList();

    private ObservableList<File> allMovies = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allMovies.clear();
        fileList.clear();
        filterByName.setVisible(true);
        filterButton.setVisible(true);
        deleteFilterButton.setVisible(false);
        header.setDirection(JFXDrawer.DrawerDirection.TOP);
        header.setSidePane(box);
        header.setDefaultDrawerSize(70);
        header.setOverLayVisible(false);
        header.setResizableOnDrag(true);
        DropShadow effect = new DropShadow();
        effect.setColor(Color.BLACK);
        effect.setOffsetX(0f);
        effect.setOffsetY(0f);
        effect.setHeight(120);
        header.setEffect(effect);
        header.open();


        pane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                grid.setMinHeight(newValue.doubleValue());
            }
        });

        pane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                grid.setMinWidth(newValue.doubleValue());
            }
        });

        /*grid.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (fileList.size() > 0) {
                    rows = (int) ((fileList.size() / (newValue.doubleValue() / 160)) + 1);
                    columns = fileList.size() / rows;
                }
            }
        });*/

        drawer.setSidePane(toolbarContent);
        drawer.setDefaultDrawerSize(300);
        drawer.toBack();

        drawer.onDrawerOpenedProperty().addListener(new ChangeListener<EventHandler<JFXDrawerEvent>>() {
            @Override
            public void changed(ObservableValue<? extends EventHandler<JFXDrawerEvent>> observable, EventHandler<JFXDrawerEvent> oldValue, EventHandler<JFXDrawerEvent> newValue) {
                drawer.toFront();
            }
        });

        drawer.onDrawerClosedProperty().addListener(new ChangeListener<EventHandler<JFXDrawerEvent>>() {
            @Override
            public void changed(ObservableValue<? extends EventHandler<JFXDrawerEvent>> observable, EventHandler<JFXDrawerEvent> oldValue, EventHandler<JFXDrawerEvent> newValue) {
                drawer.toBack();
            }
        });

        transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                drawer.close();
                drawer.setOnDrawerClosed(new EventHandler<JFXDrawerEvent>() {
                    @Override
                    public void handle(JFXDrawerEvent event) {
                        drawer.toBack();
                    }
                });
            } else {
                drawer.open();
                drawer.toFront();
            }
        });
        String path = null;
        try {
            path = URLDecoder.decode("C:/Users/Usuario/Desktop/Tic1/proyeccto psota postaposta/tic1_client_server/client/src/main/resources/movie_crud/ui/images/movieImages", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        File folder = new File(path);

        allMovies.addAll(Arrays.asList(folder.listFiles()));

        loadMovies(allMovies);

    }

    private void addImage(int index, int colIndex, int rowIndex) {

        hb.setAlignment(Pos.CENTER);

        /*Label name = new Label(id);
        name.setFont(Font.font("Arial"));
//        hb.getChildren().add(name);

        hb.setOnMouseEntered(e -> {
            if (!name.isVisible()) name.setVisible(true);
        });

        hb.setOnMouseExited(e -> {
            if (name.isVisible()) {
                name.setVisible(false);
            }
        });
*/
        String idToCut = fileList.get(index).getName();
        String id = idToCut.substring(0, (idToCut.length() - 4));
        // System.out.println(id);
        // System.out.println(fileList.get(i).getName());
        image = new Image(fileList.get(index).toURI().toString());
        pic = new ImageView();
        pic.setFitWidth(160);
        pic.setFitHeight(220);
        pic.setImage(image);
        pic.setId(id);
        hb.getChildren().add(pic);
//        hb.getChildren().add(name);
        GridPane.setConstraints(pic, colIndex, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER);
        // grid.add(pic, imageCol, imageRow);
        grid.getChildren().addAll(pic);

        pic.getStyleClass().add("pics");
        pic.setOnMouseEntered(e -> {

        });

        Tooltip.install(pic, new Tooltip(id));
        pic.setOnMouseClicked(e -> {
            try {
                List<Movie> a = movieMgr.filterTitlePaged(id, 0);
                Movie selectedForPreview = movieMgr.filterTitlePaged(id, 0).get(0); // Manera de asociar la foto con la pelicula.

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
                Parent root = fxmlLoader.load(MovieDetailsController.class.getResourceAsStream("/movie_crud/ui/movie/MovieDetails.fxml"));
                movieDetails = root;
                MovieDetailsController movieDetailsController = fxmlLoader.getController();
                movieDetailsController.setMovieDetails(selectedForPreview);
                movieDetailsController.loadData(selectedForPreview);
                filterByName.setVisible(false);
                filterButton.setVisible(false);

                mainContent.getChildren().removeAll();
                mainContent.getChildren().setAll(root);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    public void goBack(AnchorPane root) {
        System.out.println("entro");
        mainContent.getChildren().removeAll(root);
        System.out.println("saco todo");
        mainContent.getChildren().setAll(homeContent);
    }

    @FXML
    void home(ActionEvent event) throws IOException {
        drawer.close();
        drawer.toBack();
        hamburgerTransition(hamburger);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(EndUserController.class.getResourceAsStream("/movie_crud/ui/client/EndUser.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((JFXButton) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(LoginController.class.getResourceAsStream("/movie_crud/ui/login/Login.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        close(event);
    }

    @FXML
    private void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void movies(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(MovieListController.class.getResourceAsStream("/movie_crud/ui/client/MovieList.fxml"));
        MovieListController movieListController = fxmlLoader.getController();
        drawer.close();
        drawer.toBack();
        filterByName.setVisible(false);
        filterButton.setVisible(false);
        hamburgerTransition(hamburger);
        mainContent.getChildren().removeAll();
        mainContent.getChildren().setAll(root);
    }

    @FXML
    void settings(ActionEvent event) {

    }

    private void hamburgerTransition(JFXHamburger hamburger) {
        transition.setRate(transition.getRate() * -1);
        transition.play();
    }

    private void loadMovies(List<File> movies) {
        fileList.clear();
        grid.getChildren().clear();
        fileList.addAll(movies);

        grid.setPadding(new Insets(7, 7, 7, 7));
        grid.setHgap(10);
        grid.setVgap(10);

        columns = 1000 / 160;
        rows = (int) ((fileList.size() / columns) + 1);
        int imageIndex = 0;
        if (movies.isEmpty()) {
            Label label = new Label();
            label.setText("No se encontraron resultados.");

            grid.setAlignment(Pos.TOP_CENTER);
            grid.getChildren().add(label);
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (imageIndex < fileList.size()) {
                        addImage(imageIndex, j, i);
                        imageIndex++;
                    }
                }
            }
        }
    }

    @FXML
    private void filter(ActionEvent event) {

        String movie = filterByName.getText().toLowerCase();
        deleteFilterButton.setVisible(true);

        List<File> filteredList = new ArrayList<>(allMovies);
        filteredList.removeIf(s -> !s.getName().toLowerCase().contains(movie));

        loadMovies(filteredList);
    }

    @FXML
    private void removeFilter(ActionEvent event) {
        filterByName.clear();
        loadMovies(allMovies);
        deleteFilterButton.setVisible(false);
    }

    @FXML
    void removeFilters(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            filterByName.clear();
            loadMovies(allMovies);
            deleteFilterButton.setVisible(false);
        }
    }

}
