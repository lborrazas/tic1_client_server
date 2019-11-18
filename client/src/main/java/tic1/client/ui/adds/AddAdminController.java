package tic1.client.ui.adds;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.springframework.stereotype.Controller;
import tic1.client.models.Provider;
import tic1.client.services.ProviderRestTemplate;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AddAdminController implements Initializable {

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXPasswordField userPassword;

    @FXML
    private JFXTextField role;

    @FXML
    private JFXComboBox<Provider> providerName;

    @FXML
    void addAdmin(ActionEvent event) {

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

        /*ObservableList<Provider> providers = FXCollections.observableArrayList(providerRestTemplate.findAll());

        providerName.setItems(providers);*/
    }
}
