package tic1.client.ui.adds;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.*;
import tic1.client.services.CinemaRestTemplate;
import tic1.client.services.FuncionRestTemplate;
import tic1.client.services.MovieRestTemplate;
import tic1.client.services.SalaRestTemplate;
import tic1.client.ui.PrincipalManagerController;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class AddFunctionController implements Initializable {

    @Autowired
    private MovieRestTemplate movieRestTemplate;

    @Autowired
    private CinemaRestTemplate cinemaRestTemplate;

    @Autowired
    private SalaRestTemplate salaRestTemplate;

    @Autowired
    private FuncionRestTemplate funcionRestTemplate;

    private PrincipalManagerController principal;

    @Autowired
    public AddFunctionController(PrincipalManagerController principal) {
        this.principal = principal;
    }

    private UserManager userManager;

    @FXML
    private JFXComboBox<Cinema> cinemaName;

    @FXML
    private JFXComboBox<Sala> salaName;

    @FXML
    private JFXComboBox<Movie> movieName;

    @FXML
    private DatePicker datePicker;

    @FXML
    private JFXComboBox<LocalTime> timePicker;

    private ObservableList<LocalDate> selectedDates = FXCollections.observableArrayList();

    private boolean isEditing;

    private Funcion funcionForEdit;

    @FXML
    void addFunction(ActionEvent event) {
        Cinema cinema = cinemaName.getSelectionModel().getSelectedItem();
        long salaId = salaName.getSelectionModel().getSelectedItem().getId();
        Movie movie = movieName.getSelectionModel().getSelectedItem();
        List<LocalDate> dates = selectedDates;
        LocalTime time = LocalTime.now();

        LocalDateTime dateAndTime = LocalDateTime.of(dates.get(0), time);

        Funcion newFunction = new Funcion();

        newFunction.setMovie(movie);
        newFunction.setCinemaId(cinema.getId());
        newFunction.setSalaId(salaId);
        newFunction.setDate(dateAndTime);

        funcionRestTemplate.save(newFunction);

        principal.refreshTable();

        close(event);
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userManager = ClientApplication.userManager;

        salaName.setDisable(true);

        datePicker.setDisable(true);

        timePicker.setDisable(true);

        selectedDates.clear();

        Callback<ListView<Cinema>, ListCell<Cinema>> factory1 = lv1 -> new ListCell<Cinema>() {

            @Override
            protected void updateItem(Cinema item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };

        cinemaName.setCellFactory(factory1);
        cinemaName.setButtonCell(factory1.call(null));

        Callback<ListView<Movie>, ListCell<Movie>> factory3 = lv1 -> new ListCell<Movie>() {

            @Override
            protected void updateItem(Movie item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };

        movieName.setCellFactory(factory3);
        movieName.setButtonCell(factory3.call(null));

        ObservableList<Movie> movies =
                FXCollections.observableArrayList(movieRestTemplate.findAllPaged(0));

        movieName.setItems(movies);

        Callback<ListView<Sala>, ListCell<Sala>> factory2 = lv1 -> new ListCell<Sala>() {

            @Override
            protected void updateItem(Sala item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };

        salaName.setCellFactory(factory2);
        salaName.setButtonCell(factory2.call(null));

        ObservableList<Cinema> cinemas =
                FXCollections.observableArrayList(cinemaRestTemplate.getByProvider(userManager.getProvider()));

        cinemaName.setItems(cinemas);

        datePicker.setOnAction(event -> selectedDates.add(datePicker.getValue()));

        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        boolean alreadySelected = selectedDates.contains(item);
                        setDisable(alreadySelected);
                        setStyle(alreadySelected ? "-fx-background-color: #09a30f;" : "");
                    }
                };
            }
        });
    }


    public void loadFuncionData(Funcion funcion) {
        try {

            /*cinemaName.getSelectionModel().select(funcion.getMovie().getName());
            salaName.getSelectionModel().select(funcion.getSalaId());*/

            isEditing = true;
            funcionForEdit = funcion;

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectedCinema(ActionEvent event) {

        if (!cinemaName.getSelectionModel().isEmpty()) {

            salaName.setDisable(false);

            long cinemaId = cinemaName.getSelectionModel().getSelectedItem().getId();

            ObservableList<Sala> salas =
                    FXCollections.observableArrayList(salaRestTemplate.getByCinemaId(cinemaId));

            salaName.setItems(salas);

        }

    }

    @FXML
    private void selectedSala(ActionEvent event) {

        if (!salaName.getSelectionModel().isEmpty()) {

            datePicker.setDisable(false);
            timePicker.setDisable(false);

        }

    }

}
