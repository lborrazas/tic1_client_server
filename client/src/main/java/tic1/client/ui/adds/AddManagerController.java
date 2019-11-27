package tic1.client.ui.adds;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.UserClient;
import tic1.client.models.UserManager;
import tic1.client.services.UserRestTemplate;
import tic1.client.services.alert.AlertMaker;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Controller
public class AddManagerController implements Initializable {

    @Autowired
    private UserRestTemplate userRestTemplate;

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXPasswordField rePasswordField;

    @FXML
    void addManager(ActionEvent event) {
        String username = usernameField.getText();

        String password = passwordField.getText();

        String rePassword = rePasswordField.getText();

        if (password != null && !password.equals("") && password.equals(rePassword)) {

            if (userRestTemplate.userExists(username)) {

                AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Usuario ya existe", "Cero originalidad papa");

            } else {

                UserManager newUser = new UserManager();

                newUser.setUsername(username);

                newUser.setPassword(password);

                newUser.setProvider(ClientApplication.userManager.getProvider());

                userRestTemplate.createUser(newUser);

                AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Enhorabuena!", "Usuario creado con exito.");

                close(event);
            }

        } else {

            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Error", "Las contraseñas no coinciden.");

        }
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
