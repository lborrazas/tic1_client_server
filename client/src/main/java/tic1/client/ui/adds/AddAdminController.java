package tic1.client.ui.adds;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AddAdminController implements Initializable {

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXPasswordField userPassword;

    @FXML
    void addAdmin(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
