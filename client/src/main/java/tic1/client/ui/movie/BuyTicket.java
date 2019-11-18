package tic1.client.ui.movie;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Controller;
import tic1.client.models.Sala;
import tic1.client.models.Seat;
import tic1.client.models.Ticket;
import tic1.commons.transfers.TicketDTO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class BuyTicket {
    @FXML
    public Button buy;
    @FXML
    private Pane butacas;


    private Sala sala;// la sala de la funcion
    private List<Ticket> tickets; // estos serian todos los tickets de la funcion
    private List<Ticket> seats;// esto es lo que va en el post

    public void getSala(Sala sala) {
        this.sala = sala;
    }

    public  void  getTikets( List<Ticket> tickets){
        this.tickets=tickets;
    }


    public void initialize(URL location, ResourceBundle resources) {
        //cargo la sala
        for (int n = 0; n < sala.getMaxfila(); n++) {
            for (int m = 0; m < sala.getMaxcolum(); m++) {
                int a = 13 + ((m / 2) - (m% 2))*(~m);
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
        }

        //cargo los asientos rojos
        for (Ticket ticket:tickets) {
            if(ticket.isBought()){
                int m = (int) ticket.getSeat().getColumna();
                int a = 13 + ((m / 2) - (m% 2))*(~m);
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
            if (button.getStyle().contains("fx-background-color:#3ED715")){
                button.setStyle("fx-background-color:#FFFFFF");
                seats.remove(ticketTemp);
            }
                else {
                button.setStyle("fx-background-color:#3ED715");
                seats.add(ticketTemp);
            }
        }

    }


}
