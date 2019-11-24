package tic1.client.ui.movie;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.*;
import tic1.client.services.TransaccionRestTemplate;
import tic1.commons.transfers.TicketDTO;
import tic1.commons.transfers.TransaccionDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.*;
import tic1.client.services.MovieRestTemplate;
import tic1.client.services.alert.ImageRestTemplate;
import tic1.client.ui.Principal2;
import tic1.client.ui.client.EndUserController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
public class BuyTicket {
    @Autowired
    TransaccionRestTemplate transaccionRestTemplate;

    @FXML private AnchorPane root;


    @FXML
    public Button buy;
    @FXML
    private Pane butacas;
    @FXML
    private JFXButton backButton;


    private UserClient client;
    private Funcion funcion;
    private Sala sala;// la sala de la funcion
    private List<Ticket> tickets; // estos serian todos los tickets de la funcion
    private List<Ticket> seats;// esto es lo que va en el post

    public void getSala(Sala sala) {
        this.sala = sala;
    }

    public void getTikets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    @FXML
    public void goToDetails(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(MovieDetailsController.class.getResourceAsStream("/movie_crud/ui/movie/MovieDetails.fxml"));
        MovieDetailsController movieDetailsController = fxmlLoader.getController();
        movieDetailsController.loadData(movieDetailsController.getMovieDetails() );
        Scene scene = backButton.getScene();
        root.translateXProperty().set(-scene.getWidth());

        //rootContainer.getChildren().add(root);

        //Timeline timeline1 = new Timeline();
     //   KeyValue kv1 = new KeyValue(this.root.translateXProperty(), (2 * scene.getWidth()), Interpolator.EASE_OUT);
        //KeyFrame kf1 = new KeyFrame(Duration.seconds(0.7), kv1);
        //timeline1.getKeyFrames().add(kf1);
      //  timeline1.setOnFinished(t -> {
        //    rootContainer.getChildren().remove(this.root);
        //});

        //Timeline timeline2 = new Timeline();
        //KeyValue kv2 = new KeyValue(rootContainer.translateXProperty(), scene.getWidth(), Interpolator.EASE_IN);
       // KeyFrame kf2 = new KeyFrame(Duration.seconds(0.7), kv2);
       // timeline2.getKeyFrames().add(kf2);
       // timeline1.play();
       // timeline2.play();
    }
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("movie_crud/ui/movie/CompraTiket.fxml"));

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }

    public void initialize(URL location, ResourceBundle resources) {
        Scene scene = root.getScene();
        FXMLLoader drawerContentLoader = new FXMLLoader(getClass().getResource("movie_crud/ui/movie/CompraTicket"));

        //cargo la sala
        for (int n = 1; n <= sala.getMaxfila(); n++) {
            for (int m = 1; m <= sala.getMaxcolum(); m++) {
                int a = 13 + ((m / 2) - (m % 2)) * (~m);
                Pane filan = (Pane) butacas.getChildren().get(n);

                Button asientoa = (Button) filan.getChildren().get(a);
                asientoa.setStyle("fx-background-color:  #FFFFFF");
                asientoa.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override

                    public void handle(MouseEvent click) {
                        this.comprar(asientoa.getId());
                    } // si no anda es porque se eta queadno con el urlimo id del for y entoces java es una kk

                    private void comprar(String id) {
                        BuyTicket.this.comprar(id);
                    }
                });

            }

           // Scene scene = new Scene(root, 300, 275);

           // stage.setTitle("FXML Welcome");
           // stage.setScene(scene);
           // stage.show();
        }

        //cargo los asientos rojos
        for (Ticket ticket : tickets) {
            if (ticket.isBought()) {
                int m = (int) ticket.getSeat().getColumna();
                int a = 13 + ((m / 2) - (m % 2)) * (~m);
                Pane filan = (Pane) butacas.getChildren().get((int) ticket.getSeat().getFila());
                Button asientoa = (Button) filan.getChildren().get(a);
                asientoa.setStyle("-fx-background-color: red");


            }
        }
    }

    private void comprar(String id) {
        TicketDTO ticketDTO = new TicketDTO();
        Ticket ticketTemp = new Ticket(ticketDTO);
        for (Ticket ticket : tickets) {
            if (id.equals("f" + ticket.getSeat().getFila() + "c" + ticket.getSeat().getColumna())) {
                ticketTemp = ticket;
            }
        }
        if (!ticketTemp.isBought() || ticketTemp.isLock()) {
            Pane filas = (Pane) butacas.getChildren().get((int) ticketTemp.getSeat().getFila());
            Button button = (Button) filas.getChildren().get((int) ticketTemp.getSeat().getColumna());
            if (button.getStyle().contains("fx-background-color:#3ED715")) {
                button.setStyle("fx-background-color:#FFFFFF");
                seats.remove(ticketTemp);
            } else {
                button.setStyle("fx-background-color:#3ED715");
                seats.add(ticketTemp);
            }
        }

    }

    @FXML
    private void buy(MouseEvent event) {
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        Transaccion transaccion = new Transaccion(transaccionDTO);
        transaccion.setClient(client.getId());
        transaccionDTO.setClient(client.getId()); //si cambiamos a que el precio de un asiento este en la funcion y no en el ticekt?? IMprotante Mate jope habria que camvair la senteidades pero ahi pdoraimos mostrar el precio despues de realizar al compra sin tener que ir a la base de  datos


        transaccionRestTemplate.create(transaccion,seats);
    }


}
