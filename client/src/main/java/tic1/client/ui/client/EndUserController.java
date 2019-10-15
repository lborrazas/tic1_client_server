package com.example.movie_crud.ui.client;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class EndUserController {

    @FXML
    private GridPane main_grid;

    @FXML
    private HBox theatre_carousel;

    @FXML
    private HBox movie_carousel;

    @FXML
    private GridPane tiles;

    @FXML
    private GridPane footer;

    private List<String> list = new ArrayList<>();
    private int j = 0;
    private double orgCliskSceneX, orgReleaseSceneX;
    private ImageView imageView;

    public void populateCarousel() {

        try {
            list.add("com/example/movie_crud/ui/images/carousel-test/A.jpg");
            list.add("com/example/movie_crud/ui/images/carousel-test/A1.jpg");
            list.add("com/example/movie_crud/ui/images/carousel-test/A6.jpg");
            list.add("com/example/movie_crud/ui/images/carousel-test/A-11.jpg");
            list.add("com/example/movie_crud/ui/images/carousel-test/A-11.jpg");

            Button lButton = new Button("<");
            Button rButton = new Button(">");

            lButton.setMaxWidth(20);
            rButton.setMaxWidth(20);

            Image[] images = new Image[list.size()];
            for (int i = 0; i < list.size(); i++) {
                images[i] = new Image(list.get(i));
            }

            ImageView imageView1 = new ImageView(images[j % list.size()]);
            ImageView imageView2 = new ImageView(images[(j + 1) % list.size()]);
            ImageView imageView3 = new ImageView(images[(j + 2) % list.size()]);
            ImageView imageView4 = new ImageView(images[(j + 3) % list.size()]);
            ImageView imageView5 = new ImageView(images[(j + 4) % list.size()]);
            //imageView.setCursor(Cursor.CLOSED_HAND);

            //imageView.setOnMousePressed(circleOnMousePressedEventHandler);

            /*imageView.setOnMouseReleased(e -> {
                orgReleaseSceneX = e.getSceneX();
                if (orgCliskSceneX > orgReleaseSceneX) {
                    lButton.fire();
                } else {
                    rButton.fire();
                }
            });*/

            rButton.setOnAction(e -> {
                j = (j + 1) % list.size();

                imageView1.setImage(images[(j) % list.size()]);
                imageView2.setImage(images[(j + 1) % list.size()]);
                imageView3.setImage(images[(j + 2) % list.size()]);
                imageView4.setImage(images[(j + 3) % list.size()]);
                imageView5.setImage(images[(j + 4) % list.size()]);

            });
            lButton.setOnAction(e -> {
                j = (j - 1) % list.size();
                if (j == 0 || j > list.size() + 1 || j == -1) {
                    j = list.size() - 1;
                }

                imageView1.setImage(images[(j) % list.size()]);
                imageView2.setImage(images[(j + 1) % list.size()]);
                imageView3.setImage(images[(j + 2) % list.size()]);
                imageView4.setImage(images[(j + 3) % list.size()]);
                imageView5.setImage(images[(j + 4) % list.size()]);

            });

            imageView1.setFitHeight(250);
            imageView1.setFitWidth(150);
            imageView2.setFitHeight(250);
            imageView2.setFitWidth(150);
            imageView3.setFitHeight(250);
            imageView3.setFitWidth(150);
            imageView4.setFitHeight(250);
            imageView4.setFitWidth(150);
            imageView5.setFitHeight(250);
            imageView5.setFitWidth(150);

            movie_carousel.setSpacing(15);
            movie_carousel.setAlignment(Pos.CENTER);
            movie_carousel.getChildren().addAll(lButton, imageView1, imageView2, imageView3, imageView4, imageView5, rButton);
            //hBox.getChildren().addAll(imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            orgCliskSceneX = t.getSceneX();
        }
    };
}
