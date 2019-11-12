package tic1.client.ui.adds;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AddActorController implements Initializable {

    @FXML
    private JFXTextField actorName;

    @FXML
    private JFXTextField actorYear;

    @FXML
    void addActor(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
