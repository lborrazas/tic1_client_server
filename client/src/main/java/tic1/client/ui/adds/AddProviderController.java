package tic1.client.ui.adds;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

//        providerRestTemplate.createProvider(provider);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
