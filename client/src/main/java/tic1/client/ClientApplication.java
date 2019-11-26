package tic1.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import tic1.client.models.UserAdmin;
import tic1.client.models.UserClient;
import tic1.client.models.UserManager;

import java.io.IOException;

import javafx.application.Application;
import tic1.client.ui.login.LoginController;

@SpringBootApplication
public class ClientApplication extends Application {

    private static ConfigurableApplicationContext context;

    private Parent root;

    public static UserClient userClient;

    public static UserAdmin userAdmin;

    public static UserManager userManager;

    public void init() throws Exception {
        ClientApplication.context = SpringApplication.run(ClientApplication.class);
    }


    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        root = fxmlLoader.load(LoginController.class.getResourceAsStream("/movie_crud/ui/login/Login.fxml"));
//		root = fxmlLoader.load(Principal2.class.getResourceAsStream("/movie_crud/ui/Principal2.fxml"));
//	root = fxmlLoader.load(EndUserController.class.getResourceAsStream("/movie_crud/ui/client/EndUser.fxml"));
//		root = fxmlLoader.load(PrincipalManagerController.class.getResourceAsStream("/movie_crud/ui/PrincipalManager.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    public void stop() {
        ClientApplication.getContext().close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

}
