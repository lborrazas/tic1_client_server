package tic1.client.ui.adds;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.models.Provider;
import tic1.client.models.UserClient;
import tic1.client.models.UserManager;
import tic1.client.services.ProviderRestTemplate;
import tic1.client.services.UserRestTemplate;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AddAdminController implements Initializable {
    @Autowired
    UserRestTemplate uRT ;
    @FXML
    public JFXTextField userRole;
    @FXML
    private JFXTextField userName;

    @FXML
    private JFXPasswordField userPassword;

    @FXML
    private JFXComboBox<Provider> providerName;

    @FXML
    void addAdmin(ActionEvent event) {
        UserManager manager = new UserManager();
        manager.setUsername(userName.getText());
        manager.setPassword(userPassword.getText());
        manager.setRole(userRole.getText());
        manager.setProvider(providerName.getSelectionModel().getSelectedItem().getId());
        uRT.createUser(manager);

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

        ObservableList<Provider> providers = FXCollections.observableArrayList(providerRestTemplate.get());

        providerName.setItems(providers);
    }

    @FXML
    private void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
