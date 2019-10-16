package tic1.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class ClientApplication {
	private static ConfigurableApplicationContext context;

	private Parent root;


	public void init() throws Exception {
		ClientApplication.context = SpringApplication.run(ClientApplication.class);
	}


	public void start(Stage primaryStage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
		root = fxmlLoader.load(com.example.movie_crud.ui.Principal2.class.getResourceAsStream("Principal2.fxml"));
        /*root = fxmlLoader.load(EndUserController.class.getResourceAsStream("EndUser.fxml"));

        EndUserController endUserController = fxmlLoader.getController();
        endUserController.populateCarousel();*/
		Scene scene= new Scene(root);
		scene.getStylesheets().add("/com/example/movie_crud/ui/styles/dark-theme.css");
//        scene.setFill(Color.TRANSPARENT);
		primaryStage.setScene(scene);
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();
	}

	public void stop() { ClientApplication.getContext().close(); }

	//public static void main(String[] args) {launch(args);}

	public static ConfigurableApplicationContext getContext() {
		return context;
	}

}
