package tic1.client.ui.login;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import tic1.client.ClientApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import tic1.client.models.User;
import tic1.client.models.UserAdmin;
import tic1.client.models.UserClient;
import tic1.client.models.UserManager;
import tic1.client.services.UserRestTemplate;
import tic1.client.services.alert.AlertMaker;
import tic1.client.ui.Principal2;
import tic1.client.ui.PrincipalManagerController;
import tic1.client.ui.client.EndUserController;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class LoginController {

    @Autowired
    private UserRestTemplate userRestTemplate;

    @FXML
    private AnchorPane layer1;

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private Label b2;

    @FXML
    private Label a2;

    @FXML
    private JFXPasswordField signUpPassword;

    @FXML
    private JFXPasswordField reSignUpPassword;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private AnchorPane layer2;

    @FXML
    private JFXButton signUp;

    @FXML
    private JFXButton signIn;

    @FXML
    private JFXButton signupButton;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton forgetPassword;

    @FXML
    private FontAwesomeIconView closeButton;

    @FXML
    void initialize() {
        signIn.setVisible(false);
        a2.setVisible(false);
        signUpPassword.setVisible(false);
        reSignUpPassword.setVisible(false);
        signupButton.setVisible(false);
        closeButton.setFill(Paint.valueOf("#2a2e37"));

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
        signupButton.setVisible(true);
        b2.setVisible(false);
        signUp.setVisible(false);
        forgetPassword.setVisible(false);
        passwordField.setVisible(false);
        loginButton.setVisible(false);

        closeButton.setFill(Paint.valueOf("white"));

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
        signupButton.setVisible(false);
        b2.setVisible(true);
        signUp.setVisible(true);
        forgetPassword.setVisible(true);
        passwordField.setVisible(true);
        loginButton.setVisible(true);

        closeButton.setFill(Paint.valueOf("#2a2e37"));

        slide.setOnFinished(e -> {

        });

    }
    @FXML
    public void login2(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(EndUserController.class.getResourceAsStream("/movie_crud/ui/client/EndUser.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/movie_crud/ui/styles/dark-theme.css");
        stage.setScene(scene);
        stage.show();
        close(event);
        ClientApplication.userClient = null;
    }

    @FXML
    public void login(ActionEvent event) throws Exception {
        String username = usernameField.getText();

        String password = passwordField.getText();

        if (username.equals("") || password.equals("")) {

            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Datos faltantes", "Llena todos los datos papa");

        } else {

            if (userRestTemplate.userExists(username)) {

                Object user = userRestTemplate.findByName(username);

                User userValidation = (User) user;

                if (!password.equals(userValidation.getPassword())) {

                    AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Contraeña incorrecta", "Ingrese nuevamente");

                } else {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

                    if (user instanceof UserClient) {

                        ClientApplication.userClient = (UserClient) user;

                        Parent root = fxmlLoader.load(EndUserController.class.getResourceAsStream("/movie_crud/ui/client/EndUser.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add("/movie_crud/ui/styles/dark-theme.css");
                        stage.setScene(scene);
                        stage.show();
                        close(event);
                    }

                    if (user instanceof UserAdmin) {

                        ClientApplication.userAdmin = (UserAdmin) user;

                        Parent root = fxmlLoader.load(Principal2.class.getResourceAsStream("/movie_crud/ui/Principal2.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add("/movie_crud/ui/styles/dark-theme.css");
                        stage.setScene(scene);
                        stage.show();
                        close(event);
                    }

                    if (user instanceof UserManager) {

                        ClientApplication.userManager = (UserManager) user;

                        Parent root = fxmlLoader.load(PrincipalManagerController.class.getResourceAsStream("/movie_crud/ui/PrincipalManager.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add("/movie_crud/ui/styles/dark-theme.css");
                        stage.setScene(scene);
                        stage.show();
                        close(event);
                    }
                }
            }else {
                AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Usuario no existe", "Dani ponenos 12");

            }
        }
    }

    @FXML
    private void signup(ActionEvent event) {
        String username = usernameField.getText();

        String password = signUpPassword.getText();

        String rePassword = reSignUpPassword.getText();

        if (password != null && !password.equals("") && password.equals(rePassword)) {

            if (userRestTemplate.userExists(username)) {

                AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Usuario ya existe", "Cero originalidad papa");

            } else {

                UserClient newUser = new UserClient();

                newUser.setUsername(username);

                newUser.setPassword(password);

                newUser.setCreditCard("0");

                userRestTemplate.createUser(newUser);

                AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Bienvenido!", "Usuario creado con exito.");

                goToLogin();

            }

        } else {

            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Error", "Las contraseñas no coinciden.");

        }
    }

    @FXML
    private void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void goToLogin() {

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
        signupButton.setVisible(false);
        b2.setVisible(true);
        signUp.setVisible(true);
        forgetPassword.setVisible(true);
        passwordField.setVisible(true);
        loginButton.setVisible(true);

        slide.setOnFinished(e -> {

        });
    }

    @FXML
    void signUpWithEnter(ActionEvent event) {
            signup(event);
    }

    @FXML
    void loginWithEnter(ActionEvent event) throws Exception {
            login(event);
    }
}