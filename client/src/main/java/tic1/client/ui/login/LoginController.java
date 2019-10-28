package tic1.client.ui.login;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tic1.client.ClientApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import tic1.client.ui.Principal2;

@Controller
public class LoginController {

    @FXML
    private AnchorPane layer1;

    @FXML
    private Label b2;

    @FXML
    private Label a2;

    @FXML
    private JFXTextField signUpPassword;

    @FXML
    private JFXTextField reSignUpPassword;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private AnchorPane layer2;

    @FXML
    private JFXButton signUp;

    @FXML
    private JFXButton signIn;

    @FXML
    private JFXButton forgetPassword;

    @FXML
    void initialize() {
        signIn.setVisible(false);
        a2.setVisible(false);
        signUpPassword.setVisible(false);
        reSignUpPassword.setVisible(false);

    }

    @FXML
    public void btn(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(500);
        slide.play();

        layer1.setTranslateX(-300);
        signIn.setVisible(true);
        a2.setVisible(true);
        signUpPassword.setVisible(true);
        reSignUpPassword.setVisible(true);
        b2.setVisible(false);
        signUp.setVisible(false);
        forgetPassword.setVisible(false);
        passwordField.setVisible(false);

        slide.setOnFinished(e -> {

        });

    }

    @FXML
    public void btn2(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(0);
        slide.play();

        layer1.setTranslateX(0);
        signIn.setVisible(false);
        a2.setVisible(false);
        signUpPassword.setVisible(false);
        reSignUpPassword.setVisible(false);
        b2.setVisible(true);
        signUp.setVisible(true);
        forgetPassword.setVisible(true);
        passwordField.setVisible(true);

        slide.setOnFinished(e -> {

        });

    }

    @FXML
    void login(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(Principal2.class.getResource("Principal.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}