package tic1.client.ui.client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXHamburger;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.Movie;
import tic1.client.services.MovieRestTemplate;
import tic1.client.ui.movie.MovieDetailsController;

@Controller
public class EndUserController implements Initializable {

    @Autowired
    private MovieRestTemplate movieMgr;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXDrawer header;

    @FXML
    private HBox box;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane pane;

    @FXML
    private ImageView pic;

    @FXML
    private Image image;

    @FXML
    String id;

    private ArrayList<File> fileList = new ArrayList<>();

    private HBox hb = new HBox();

    private static final String LEFT = "LEFT";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        header.setDirection(JFXDrawer.DrawerDirection.TOP);
        header.setSidePane(box);
        header.setDefaultDrawerSize(70);
        header.setOverLayVisible(false);
        header.setResizableOnDrag(true);
        DropShadow effect = new DropShadow();
        effect.setColor(Color.BLACK);
        effect.setOffsetX(0f);
        effect.setOffsetY(0f);
        effect.setHeight(120);
        header.setEffect(effect);
        header.open();

        pane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                grid.setMinHeight(newValue.doubleValue());
            }
        });

        pane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                grid.setMinWidth(newValue.doubleValue());
            }
        });

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/movie_crud/ui/client/toolbar.fxml"));
            VBox box = loader.load();
            drawer.setSidePane(box);
            drawer.setDefaultDrawerSize(300);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });

//        String path = "/com/example/movie_crud/ui/images/movieImages/";
        String path = null;
        try {
            path = URLDecoder.decode("C:/Users/jpalg/Desktop/TIC1/movie-crud/src/main/resources/com/example/movie_crud/ui/images/movieImages", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        File folder = new File(path);
        // pushing single path files in the array filelist1
        fileList.addAll(Arrays.asList(folder.listFiles()));

        grid.setPadding(new Insets(7,7,7,7));
        // setting interior grid padding
        grid.setHgap(10);
        grid.setVgap(10);
        // grid.setGridLinesVisible(true);

        int rows = (int) ((fileList.size() / (this.root.getWidth() / 160)) + 1);
        int columns = 6;
        int imageIndex = 0;

        for (int i = 0 ; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (imageIndex < fileList.size()) {
                    addImage(imageIndex, j, i);
                    imageIndex++;
                }
            }
        }
    }

    private void addImage(int index, int colIndex, int rowIndex) {

        hb.setAlignment(Pos.CENTER);


        /*Label name = new Label(id);
        name.setFont(Font.font("Arial"));
//        hb.getChildren().add(name);

        hb.setOnMouseEntered(e -> {
            if (!name.isVisible()) name.setVisible(true);
        });

        hb.setOnMouseExited(e -> {
            if (name.isVisible()) {
                name.setVisible(false);
            }
        });
*/
        String idToCut = fileList.get(index).getName();
        String id = idToCut.substring(0, (idToCut.length() - 4));
        // System.out.println(id);
        // System.out.println(fileList.get(i).getName());
        image = new Image(fileList.get(index).toURI().toString());
        pic = new ImageView();
        pic.setFitWidth(160);
        pic.setFitHeight(220);
        pic.setImage(image);
        pic.setId(id);
        hb.getChildren().add(pic);
//        hb.getChildren().add(name);
        GridPane.setConstraints(pic, colIndex, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER);
        // grid.add(pic, imageCol, imageRow);
        grid.getChildren().addAll(pic);

        pic.getStyleClass().add("pics");
        pic.setOnMouseEntered(e -> {

        });

        Tooltip.install(pic, new Tooltip(id));
        pic.setOnMouseClicked(e -> {
           /* try {
                *//*Movie selectedForPreview = movieMgr.findByName(id); // Manera de asociar la foto con la pelicula.

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
                Parent root = fxmlLoader.load(MovieDetailsController.class.getResourceAsStream("MovieDetails.fxml"));

                MovieDetailsController movieDetailsController = fxmlLoader.getController();
                movieDetailsController.loadData(selectedForPreview);

                Stage stage = new Stage(StageStyle.DECORATED);
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/com/example/movie_crud/ui/styles/dark-theme.css");
                stage.setScene(scene);
                stage.show();*//*
            } catch (IOException ex) {
                ex.printStackTrace();
            }*/
        });
    }

    @FXML
    void show(ScrollEvent event) {
        if (!header.isOpened() || !header.isOpening()) header.open();
    }

    @FXML
    void hide(ScrollEvent event) {
        if (!header.isClosed() || !header.isClosing()) header.close();
    }
}
