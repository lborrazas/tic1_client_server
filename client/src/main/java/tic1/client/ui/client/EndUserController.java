package tic1.client.ui.client;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

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
    private int j = 0, k = 0;
    private double orgCliskSceneX, orgReleaseSceneX;
    private ImageView imageView;

    public void populateMovieCarousel() {

        try {
            list.add("movie_crud/ui/images/carousel-test/A.jpg");
            list.add("movie_crud/ui/images/carousel-test/A1.jpg");
            list.add("movie_crud/ui/images/carousel-test/A6.jpg");
            list.add("movie_crud/ui/images/carousel-test/A-11.jpg");
            list.add("movie_crud/ui/images/carousel-test/A-11.jpg");

            Button lButton = new Button();
            Button rButton = new Button();

            MaterialDesignIconView leftArrow = new MaterialDesignIconView(MaterialDesignIcon.CHEVRON_LEFT);
            MaterialDesignIconView rightArrow = new MaterialDesignIconView(MaterialDesignIcon.CHEVRON_RIGHT);

            leftArrow.getStyleClass().add("glyph-icon-carousel");
            rightArrow.getStyleClass().add("glyph-icon-carousel");

            lButton.setGraphic(leftArrow);
            rButton.setGraphic(rightArrow);

            lButton.getStyleClass().add("carousel-button");
            rButton.getStyleClass().add("carousel-button");

            lButton.setMaxWidth(20);
            rButton.setMaxWidth(20);
            lButton.setMinWidth(20);
            rButton.setMinWidth(20);

            Image[] images = new Image[list.size()];
            for (int i = 0; i < list.size(); i++) {
                images[i] = new Image(list.get(i));
            }

            ImageView imageView1 = new ImageView(images[k % list.size()]);
            ImageView imageView2 = new ImageView(images[(k + 1) % list.size()]);
            ImageView imageView3 = new ImageView(images[(k + 2) % list.size()]);
            ImageView imageView4 = new ImageView(images[(k + 3) % list.size()]);
            ImageView imageView5 = new ImageView(images[(k + 4) % list.size()]);
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

            lButton.setOnAction(e -> {
                k = (k + 1) % list.size();

                imageView1.setImage(images[(k) % list.size()]);
                imageView2.setImage(images[(k + 1) % list.size()]);
                imageView3.setImage(images[(k + 2) % list.size()]);
                imageView4.setImage(images[(k + 3) % list.size()]);
                imageView5.setImage(images[(k + 4) % list.size()]);

            });
            rButton.setOnAction(e -> {
                k = (k - 1) % list.size();
                if (k == 0 || k > list.size() + 1 || k == -1) {
                    k = list.size() - 1;
                }

                imageView1.setImage(images[(k) % list.size()]);
                imageView2.setImage(images[(k + 1) % list.size()]);
                imageView3.setImage(images[(k + 2) % list.size()]);
                imageView4.setImage(images[(k + 3) % list.size()]);
                imageView5.setImage(images[(k + 4) % list.size()]);

            });

            imageView1.setFitHeight(210);
            imageView1.setFitWidth(130);
            imageView2.setFitHeight(210);
            imageView2.setFitWidth(130);
            imageView3.setFitHeight(210);
            imageView3.setFitWidth(130);
            imageView4.setFitHeight(210);
            imageView4.setFitWidth(130);
            imageView5.setFitHeight(210);
            imageView5.setFitWidth(130);

            movie_carousel.setSpacing(15);
            movie_carousel.setAlignment(Pos.CENTER);
            movie_carousel.getChildren().addAll(lButton, imageView1, imageView2, imageView3, imageView4, imageView5, rButton);
            //hBox.getChildren().addAll(imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void populateTheatreCarousel() {

        try {
            list.add("movie_crud/ui/images/carousel-test/A.jpg");
            list.add("movie_crud/ui/images/carousel-test/A1.jpg");
            list.add("movie_crud/ui/images/carousel-test/A6.jpg");
            list.add("movie_crud/ui/images/carousel-test/A-11.jpg");
            list.add("movie_crud/ui/images/carousel-test/A-11.jpg");

            Button lButton = new Button();
            Button rButton = new Button();

            MaterialDesignIconView leftArrow = new MaterialDesignIconView(MaterialDesignIcon.CHEVRON_LEFT);
            MaterialDesignIconView rightArrow = new MaterialDesignIconView(MaterialDesignIcon.CHEVRON_RIGHT);

            leftArrow.getStyleClass().add("glyph-icon-carousel");
            rightArrow.getStyleClass().add("glyph-icon-carousel");

            lButton.setGraphic(leftArrow);
            rButton.setGraphic(rightArrow);

            lButton.getStyleClass().add("carousel-button");
            rButton.getStyleClass().add("carousel-button");

            lButton.setMaxWidth(20);
            rButton.setMaxWidth(20);
            lButton.setMinWidth(20);
            rButton.setMinWidth(20);

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

            lButton.setOnAction(e -> {
                j = (j + 1) % list.size();

                imageView1.setImage(images[(j) % list.size()]);
                imageView2.setImage(images[(j + 1) % list.size()]);
                imageView3.setImage(images[(j + 2) % list.size()]);
                imageView4.setImage(images[(j + 3) % list.size()]);
                imageView5.setImage(images[(j + 4) % list.size()]);

            });
            rButton.setOnAction(e -> {
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

            imageView1.setFitHeight(210);
            imageView1.setFitWidth(130);
            imageView2.setFitHeight(210);
            imageView2.setFitWidth(130);
            imageView3.setFitHeight(210);
            imageView3.setFitWidth(130);
            imageView4.setFitHeight(210);
            imageView4.setFitWidth(130);
            imageView5.setFitHeight(210);
            imageView5.setFitWidth(130);

            theatre_carousel.setSpacing(15);
            theatre_carousel.setAlignment(Pos.CENTER);
            theatre_carousel.getChildren().addAll(lButton, imageView1, imageView2, imageView3, imageView4, imageView5, rButton);
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
