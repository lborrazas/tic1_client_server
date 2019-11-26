package tic1.client.ui.adds;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import tic1.client.models.Actor;
import tic1.client.services.ActorRestTemplate;

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

        ActorRestTemplate actorRestTemplate = new ActorRestTemplate();

        Actor actor = new Actor();

        actor.setName(actorName.getText());

        actor.setYear(Integer.parseInt(actorYear.getText()));

        actorRestTemplate.createActor(actor);

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
