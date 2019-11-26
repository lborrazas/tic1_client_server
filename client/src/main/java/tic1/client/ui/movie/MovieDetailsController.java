package tic1.client.ui.movie;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.*;
import tic1.client.services.*;
import tic1.client.services.alert.ImageRestTemplate;
import tic1.client.ui.Principal2;
import tic1.client.ui.client.EndUserController;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MovieDetailsController implements Initializable {

    @Autowired
    private TicketRestTemplate ticketRestTemplate;
    @Autowired
    private MovieRestTemplate movieRestTemplate;
    @Autowired
    private SalaRestTemplate salaRestTemplate;
    @Autowired
    private FuncionRestTemplate funcionRestTemplate;
    @Autowired
    private CinemaRestTemplate cinemaRestTemplate;

    private  ClientApplication clientApplication;
    @Autowired
    public MovieDetailsController(ClientApplication clientApplication) {
    this.clientApplication=clientApplication;
    }

    @FXML
    private Text movie_name;

    @FXML
    private Text movie_duration;

    @FXML
    private Label movie_description;

    @FXML
    private Label movie_actors;

    @FXML
    private ImageView movie_image;

    @FXML
    private ComboBox<Cinema> cinema;

    @FXML
    private ComboBox<Sala> sala;

    @FXML
    private ComboBox<LocalDate> fecha;

    @FXML
    private ComboBox<LocalTime> hora;

    @FXML
    private Button buy_btn;

    @FXML
    private JFXButton minus_button;

    @FXML
    private TextField movie_quantity;

    @FXML
    private AnchorPane rootContainer;

    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private Label movie_genres;

    private int numberOfEntrances = 0;

    private String parent;

    private Movie movieDetails;

    private List<Funcion> functions;

    private List<Funcion> functionsFilter = new ArrayList<>();
    private List<Funcion> functionsFilter2 = new ArrayList<>();
    private List<Funcion> functionsFilter3 = new ArrayList<>();

    private Set<LocalDate> dates = new HashSet<>();
    private Set<LocalTime> times = new HashSet<>();
    private Set<Cinema> cinemas = new HashSet<>();
    private Set<Sala> salas = new HashSet<>();

    @FXML
    public void loadData(Movie movie, List<Funcion> functions) {

        movie_name.setText(movie.getName());
        movie_description.setText(movie.getDescription());
        movie_actors.setText(movie.getActors().stream().map(Actor::getName)
                .collect(Collectors.joining(", ")));
        movie_duration.setText(Long.toString(movie.getDuration()));
        movie_genres.setText(movie.getGenre().stream().map(Genre::getGenre)
                .collect(Collectors.joining(", ")));
        ImageRestTemplate imageRestTemplate = new ImageRestTemplate();

        this.functions = functions;

        for (Funcion funcion : this.functions) {
            dates.add(funcion.getDate().toLocalDate());
            /*times.add(funcion.getDate().toLocalTime());
            cinemas.add(cinemaRestTemplate.getOne(funcion.getCinemaId()));
            salas.add(salaRestTemplate.getById(funcion.getSalaId()));*/
        }

        ObservableList<LocalDate> fechas = FXCollections.observableArrayList(dates);
        fecha.setItems(fechas);

        try {
            movie_image.setImage(imageRestTemplate.showImage(movie.getImagePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Callback<ListView<Cinema>, ListCell<Cinema>> factory1 = lv1 -> new ListCell<Cinema>() {

            @Override
            protected void updateItem(Cinema item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };

        cinema.setCellFactory(factory1);
        cinema.setButtonCell(factory1.call(null));

        Callback<ListView<Sala>, ListCell<Sala>> factory2 = lv1 -> new ListCell<Sala>() {

            @Override
            protected void updateItem(Sala item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };

        sala.setCellFactory(factory2);
        sala.setButtonCell(factory2.call(null));

        functionsFilter.clear();
        functionsFilter2.clear();
        functionsFilter3.clear();
        dates.clear();
        times.clear();
        cinemas.clear();
        salas.clear();

        hora.setDisable(true);
        cinema.setDisable(true);
        sala.setDisable(true);

    }
    @FXML
    public void buyAction(ActionEvent event) throws IOException {

        Funcion funcion = functionsFilter3.get(0);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(BuyTicketController.class.getResourceAsStream("/movie_crud/ui/movie/BuyTicket.fxml"));
        BuyTicketController buyTicketController = fxmlLoader.getController();

        buyTicketController.loadSala(ticketRestTemplate.findByFunction_dateAndsalaid(funcion.getDate(),funcion.getSalaId()));

        Scene scene = buy_btn.getScene();
        root.translateXProperty().set(scene.getWidth());

        rootContainer.getChildren().add(root);

        Timeline timeline1 = new Timeline();
        KeyValue kv1 = new KeyValue(anchorRoot.translateXProperty(), -scene.getWidth(), Interpolator.EASE_OUT);
        KeyFrame kf1 = new KeyFrame(Duration.seconds(0.7), kv1);
        timeline1.getKeyFrames().add(kf1);
        timeline1.setOnFinished(t -> {
            rootContainer.getChildren().remove(anchorRoot);
        });

        Timeline timeline2 = new Timeline();
        KeyValue kv2 = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(0.7), kv2);
        timeline2.getKeyFrames().add(kf2);
        timeline1.play();
        timeline2.play();
    }

    public void sum() {
        if (numberOfEntrances < 20) numberOfEntrances++;
        movie_quantity.setText(String.valueOf(numberOfEntrances));
    }

    public void minus() {
        if (numberOfEntrances > 0) numberOfEntrances--;
        movie_quantity.setText(String.valueOf(numberOfEntrances));
    }

    public void goToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        if (parent != null && parent.equals("Principal2")) {
            Parent root = fxmlLoader.load(Principal2.class.getResourceAsStream("/movie_crud/ui/Principal2.fxml"));
            Scene scene = new Scene(root, 800, 500);
            Stage stage = (Stage) ((JFXButton) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            Parent root = fxmlLoader.load(EndUserController.class.getResourceAsStream("/movie_crud/ui/client/EndUser.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((JFXButton) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void selectedDate(ActionEvent event) {

        times.clear();
        cinemas.clear();
        salas.clear();
        hora.setItems(null);
        cinema.setItems(null);
        sala.setItems(null);
        functionsFilter.clear();
        functionsFilter2.clear();
        functionsFilter3.clear();

        if (!fecha.getSelectionModel().isEmpty()) {

            hora.setDisable(false);
            cinema.setDisable(true);
            sala.setDisable(true);

            LocalDate date = fecha.getSelectionModel().getSelectedItem();

            for (Funcion funcion :this.functions) {
                if (funcion.getDate().toLocalDate().equals(date)){
                    functionsFilter.add(funcion);
                    times.add(funcion.getDate().toLocalTime());
                }
            }
            hora.setItems(FXCollections.observableArrayList(times));
        }

    }

    @FXML
    private void selectedHour(ActionEvent event) {

        cinemas.clear();
        salas.clear();
        functionsFilter2.clear();
        cinema.setItems(null);
        sala.setItems(null);
        functionsFilter2.clear();
        functionsFilter3.clear();

        if (!hora.getSelectionModel().isEmpty()) {

            cinema.setDisable(false);
            sala.setDisable(true);

            LocalTime time = hora.getSelectionModel().getSelectedItem();

            for (Funcion funcion : functionsFilter) {
                if (funcion.getDate().toLocalTime().equals(time)){
                    functionsFilter2.add(funcion);
                    cinemas.add(cinemaRestTemplate.getOne(funcion.getCinemaId()));
                }
            }
            cinema.setItems(FXCollections.observableArrayList(cinemas));
        }

    }

    @FXML
    private void selectedCinema(ActionEvent event) {

        salas.clear();
        functionsFilter3.clear();
        sala.setItems(null);
        functionsFilter3.clear();

        if (!cinema.getSelectionModel().isEmpty()) {

            sala.setDisable(false);

            Cinema cinema = this.cinema.getSelectionModel().getSelectedItem();

            for (Funcion funcion : functionsFilter2) {
                if (funcion.getCinemaId() == cinema.getId()){
                    functionsFilter3.add(funcion);
                    salas.add(salaRestTemplate.getById(funcion.getSalaId()));
                }
            }
            sala.setItems(FXCollections.observableArrayList(salas));
        }
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Movie getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(Movie movieDetails) {
        this.movieDetails = movieDetails;
    }
}
