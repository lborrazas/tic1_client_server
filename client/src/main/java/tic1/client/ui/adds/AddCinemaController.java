package tic1.client.ui.adds;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AddCinemaController implements Initializable {

    @FXML
    private JFXComboBox<?> providerName;

    @FXML
    private JFXTextField cinemaName;

    @FXML
    private JFXTextField location;

    @FXML
    void addCinema(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
