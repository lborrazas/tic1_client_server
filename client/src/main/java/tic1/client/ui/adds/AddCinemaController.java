package tic1.client.ui.adds;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.stereotype.Controller;
import tic1.client.models.Actor;
import tic1.client.models.Cinema;
import tic1.client.models.Genre;
import tic1.client.models.Provider;
import tic1.client.services.ActorRestTemplate;
import tic1.client.services.CinemaRestTemplate;
import tic1.client.services.GenreRestTemplate;
import tic1.client.services.ProviderRestTemplate;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AddCinemaController implements Initializable {

    @FXML
    private JFXComboBox<Provider> providerName;

    @FXML
    private JFXTextField cinemaName;

    @FXML
    private JFXTextField location;

    @FXML
    void addCinema(ActionEvent event) {

        CinemaRestTemplate cinemaRestTemplate = new CinemaRestTemplate();

        Cinema cinema = new Cinema();
        
        cinema.setProvider(providerName.getValue().getId());

        cinema.setName(cinemaName.getText());

        cinema.setLocation(location.getText());

        cinemaRestTemplate.createCinema(cinema);

        close(event);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Callback<ListView<Provider>, ListCell<Provider>> factory = lv1 -> new ListCell<Provider>() {

            @Override
            protected void updateItem(Provider item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };

        providerName.setCellFactory(factory);

        providerName.setButtonCell(factory.call(null));

        ProviderRestTemplate providerRestTemplate = new ProviderRestTemplate();

        ObservableList<Provider> providers =
                FXCollections.observableArrayList(providerRestTemplate.get());

        this.providerName.setItems(providers);

        /*ObservableList<Provider> providers = FXCollections.observableArrayList(providerRestTemplate.findAll());

        providerName.setItems(providers);*/

    }

    @FXML
    private void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
