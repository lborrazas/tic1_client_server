package tic1.client.ui.client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.Movie;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MovieListController implements Initializable {

    @FXML
    private AnchorPane rootContainer;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private ScrollPane pane;

    @FXML
    private VBox list;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXDrawer header;

    @FXML
    private HBox box;

    @FXML
    private JFXHamburger hamburger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Movie movie = new Movie();
        movie.setName("Star Wars");
        movie.setDescription("Descjsldfiuoipwfhjfhujbhuifhjd");
        movie.setDuration(120);
        addMovie(movie);
        addMovie(movie);
        addMovie(movie);
    }

    /*<AnchorPane layoutX="236.0" prefHeight="250.0" prefWidth="200.0" AnchorPane.leftAnchor="0.0" AnchorPane.rightAnchor="0.0">
                           <children>
                              <ImageView fitHeight="220.0" fitWidth="160.0" layoutX="14.0" layoutY="15.0" pickOnBounds="true" preserveRatio="true" />
                              <Text layoutX="174.0" layoutY="38.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Nombre Pelicula">
                                 <font>
                                    <Font name="Arial" size="26.0" />
                                 </font>
                              </Text>
                              <Text layoutX="174.0" layoutY="76.0" strokeType="OUTSIDE" strokeWidth="0.0" text="desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-desc-" wrappingWidth="700.0">
                                 <font>
                                    <Font name="Arial" size="16.0" />
                                 </font>
                              </Text>
                              <Text layoutX="174.0" layoutY="139.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Actores:">
                                 <font>
                                    <Font name="Arial" size="16.0" />
                                 </font>
                              </Text>
                              <Text layoutX="391.0" layoutY="34.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Duration">
                                 <font>
                                    <Font name="Arial" size="16.0" />
                                 </font>
                              </Text>
                              <Text layoutX="253.0" layoutY="138.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Text">
                                 <font>
                                    <Font name="Arial" size="16.0" />
                                 </font>
                              </Text>
                              <JFXButton layoutX="890.0" layoutY="199.0" text="Comprar">
                                 <font>
                                    <Font size="16.0" />
                                 </font>
                              </JFXButton>
                              <MenuButton layoutX="942.0" layoutY="16.0" mnemonicParsing="false">
                                <items>
                                  <MenuItem mnemonicParsing="false" text="Action 1" />
                                  <MenuItem mnemonicParsing="false" text="Action 2" />
                                </items>
                              </MenuButton>
                           </children>
                        </AnchorPane>*/

    @FXML
    public void addMovie(Movie movie) {
        AnchorPane box = new AnchorPane();
        box.setMinWidth(list.getWidth());
        box.setPrefHeight(250);
        ImageView pic;
        Image image;
        Text nombre = new Text("nombre");
        nombre.getStyleClass().add("texts");
        nombre.setText(movie.getName());
        Text desc = new Text("desc");
        desc.getStyleClass().add("texts");
        desc.setText(movie.getDescription());
        Text duration = new Text("duration");
        duration.getStyleClass().add("texts");
        duration.setText(Long.toString(movie.getDuration()));
        Text actors = new Text("actors");
        actors.getStyleClass().add("texts");
//        actors.setText(movie.getActors());
        JFXButton comprar = new JFXButton("Comprar");

       /* image = new Image(movie.getImageID());
        pic = new ImageView();
        pic.setFitWidth(160);
        pic.setFitHeight(220);
        pic.setImage(image);
        box.getChildren().add(pic);
        pic.setLayoutX(15);
        pic.setLayoutY(15);
        pic.getStyleClass().add("pics");*/
        box.getChildren().add(nombre);
        nombre.setLayoutX(175);
        nombre.setLayoutY(40);
        box.getChildren().add(duration);
        duration.setLayoutX(391);
        duration.setLayoutY(40);
        box.getChildren().add(desc);
        desc.setLayoutY(75);
        desc.setLayoutX(175);
        desc.setWrappingWidth(700);
        box.getChildren().add(actors);
        actors.setLayoutY(140);
        actors.setLayoutX(175);
        box.getChildren().add(comprar);
        comprar.setLayoutX(890);
        comprar.setLayoutY(200);
        box.getStyleClass().add("movie-list");


//        box.getStylesheets().add("/com/example/movie_crud/ui/styles/dark-theme.css");
        list.getChildren().add(box);

    }

    @FXML
    public void goToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(EndUserController.class.getResourceAsStream("/movie_crud/ui/client/EndUser.fxml"));
        EndUserController endUserController = fxmlLoader.getController();
        endUserController.goBack();
    }
}

