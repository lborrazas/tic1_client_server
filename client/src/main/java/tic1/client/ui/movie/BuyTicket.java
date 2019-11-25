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
import javafx.scene.layout.StackPane;
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
import java.util.ArrayList;
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

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;

@Controller
public class BuyTicket {

    public static boolean isNumeric(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Autowired
    private TransaccionRestTemplate transaccionRestTemplate;

    @FXML
    public AnchorPane anchorRoot;
    @FXML
    public Button backbutton;


    @FXML
    private AnchorPane root;


    @FXML
    public Button buy;
    @FXML
    private Pane butacas;


    private UserClient client;
    private Funcion funcion;
    private Sala sala;// la sala de la funcion
    private List<Ticket> tickets; // estos serian todos los tickets de la funcion
    private List<Ticket> seats = new ArrayList<>();// esto es lo que va en el post

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;

    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setTikets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @FXML
    public void goToDetails(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(MovieDetailsController.class.getResourceAsStream("/movie_crud/ui/movie/MovieDetails.fxml"));
        MovieDetailsController movieDetailsController = fxmlLoader.getController();
        movieDetailsController.loadData(movieDetailsController.getMovieDetails());
        Scene scene = backbutton.getScene();
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
    }
    //cargo la sala
    public void empezar() {

        if (true) {
            for (int n = 1; n <= sala.getMaxfila(); n++) {
                for (int m = 1; m <= sala.getMaxcolum(); m++) {
                    int c = (int) Math.pow(-1, m);
                    int a = 12 + c * (m / 2);        // probar esto el switch de return M esta mal tendira que seguir la secuencia y creo que puse 13 11 y capaz va 13 14
                    int b = 12 - n;
                    for (int k = 0; k < butacas.getChildren().size(); k++) {

                        Pane filan = (Pane) butacas.getChildren().get(k);

                        if (filan.getId().equals("fila" + b)) {
                            for (int l = 0; l < filan.getChildren().size(); l++) {

                                Button asientoa = (Button) filan.getChildren().get(l);
                                if (asientoa.getId().equals("f" + b + "c" + a)) {
                                    asientoa.setStyle("-fx-background-color:white");
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

                            }
                        }
                    }
                }
            }

            // Scene scene = new Scene(root, 300, 275);

            // stage.setTitle("FXML Welcome");
            // stage.setScene(scene);
            // stage.show();


            //cargo los asientos rojos
            for (Ticket ticket : tickets) {
                if (ticket.isBought()) {
                    int n = (int) ticket.getSeat().getFila();
                    int m = (int) ticket.getSeat().getColumna();
                    int c = (int) Math.pow(-1, m);
                    int a = 12 + c * (m / 2);
                    int b = 12 - n;
                    for (int k = 0; k < butacas.getChildren().size(); k++) {
                        Pane filan = (Pane) butacas.getChildren().get(k);
                        if (filan.getId().equals("fila" + b))
                            for (int j = 0; j < filan.getChildren().size(); j++) {
                                Button asientoa = (Button) filan.getChildren().get(j);
                                if (asientoa.getId().equals("f" + (b) + "c" + a)) {
                                    asientoa.setStyle("-fx-background-color: red");
                                }
                            }
                    }
                }
            }
        }
    }

    private void comprar(String id) {
        String[] idArray = id.split("");
        String columna = null;
        String fila = null;
        switch (idArray.length) {
            case 4:
                fila = idArray[2];
                columna = idArray[4];
                break;
            case 5:
                if (isNumeric(idArray[2])) {
                    fila = idArray[1] + idArray[2];
                    columna = idArray[4];
                } else {
                    fila = idArray[1];
                    columna = idArray[3] + idArray[4];

                }
                break;
            case 6:
                fila = idArray[1] + idArray[2];
                columna = idArray[4] + idArray[5];
                break;
        }
        long filanm = 12 - Long.parseLong(fila);
        long columnanm = returnColum(Long.parseLong(columna));


      //  fila=filanm+"";
        //columna=columnanm+"";
        Ticket ticketTemp = new Ticket();
        for (Ticket ticket : tickets) {
            if (ticket.getSeat().getFila() == filanm &&
                    ticket.getSeat().getColumna() == columnanm) {
                ticketTemp = ticket;
            }
        }
        if (!ticketTemp.isBought() || !ticketTemp.isLock()) {
            for (int j = 0; j < butacas.getChildren().size(); j++) {
                Pane filas = (Pane) butacas.getChildren().get(j);
                if (filas.getId().equals("fila" + fila)) {
                    for (int k = 0; k < filas.getChildren().size(); k++) {
                        Button button = (Button) filas.getChildren().get(k);
                        if (button.getId().equals("f" + fila + "c" + columna)) {  // falta pregutar si el coso es rojo
                            if (button.getStyle().contains("fx-background-color:green")) { //#3ED715"
                                button.setStyle("fx-background-color:white");
                                seats.remove(ticketTemp);

                            } else if(button.getStyle().contains("fx-background-color:white")){
                                button.setStyle("fx-background-color:red"); //
                                seats.add(ticketTemp);
                            }
                        }
                    }
                }

            }
        }
    }

    private long returnColum(long a) {
        int m =0;
        switch ((int) a) {
            case 12:
                m=1;
                break;
            case 13:
                m=2;
                break;
            case 11:
                m=3;
                break;
            case 14:
                m=4;
                break;
            case 10:
                m=5;
                break;
            case 15:
                m=6;
                break;
            case 9:
                m=7;
                break;
            case 16:
                m=8;
                break;
            case 8:
                m=9;
                break;
            case 17:
                m=10;
                break;
            case 7:
                m=11;
                break;
            case 18:
                m=12;
                break;
            case 6:
                m=13;
                break;
            case 19:
                m=14;
                break;
            case 5:
                m=15;
                break;
            case 20:
                m=16;
                break;
            case 4:
                m=17;
                break;
            case 21:
                m=18;
                break;
            case 3:
                m=19;
                break;
            case 22:
                m=20;
                break;
            case 2:
                m=21;
                break;
            case 23:
                m=22;
                break;
            case 1:
                m=23;
                break;
            case 24:
                m=24;
                break;
        }
        return m; // nooo se si esto anda bien puede que este mal
        // verificar con esto
        // int c = (int) Math.pow(-1, m);
        //                    int a = 13 + c * (m / 2);
        //                    int b = 12 - n;
    }


    @FXML
    private void buy(MouseEvent event) {
        Transaccion transaccion = new Transaccion();
        //transaccion.setClient(client.getId());
       // transaccion.setClient(client.getId()); //si cambiamos a que el precio de un asiento este en la funcion y no en el ticekt?? IMprotante Mate jope habria que camvair la senteidades pero ahi pdoraimos mostrar el precio despues de realizar al compra sin tener que ir a la base de  datos
        transaccion.setClient(2); //si cambiamos a que el precio de un asiento este en la funcion y no en el ticekt?? IMprotante Mate jope habria que camvair la senteidades pero ahi pdoraimos mostrar el precio despues de realizar al compra sin tener que ir a la base de  datos

        for (int j=0;j<seats.size();j++){
            seats.get(j).setBought(true);
        }
        transaccionRestTemplate.create(transaccion, seats);
        backear();
    }


    public void back(MouseEvent mouseEvent) {
        backear();
    }

    private void backear() {

    }

}
