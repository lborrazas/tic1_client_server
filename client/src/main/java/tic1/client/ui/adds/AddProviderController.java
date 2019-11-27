package tic1.client.ui.adds;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import tic1.client.models.Provider;
import tic1.client.services.ProviderRestTemplate;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AddProviderController implements Initializable {

    @FXML
    private JFXTextField providerName;

    @FXML
    private void addProvider(ActionEvent event) {

        ProviderRestTemplate providerRestTemplate = new ProviderRestTemplate();

        Provider provider = new Provider();

        provider.setName(providerName.getText());

        providerRestTemplate.createProvider(provider);

        close(event);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
