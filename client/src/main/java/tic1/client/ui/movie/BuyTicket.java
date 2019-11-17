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
import tic1.client.models.Ticket;
import tic1.commons.transfers.TicketDTO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class BuyTicket {
    @FXML
    private Pane butacas;

    private Sala sala;
    private List<Ticket> tickets;


    public void getSala(Sala sala) {
        this.sala = sala;
    }


    public void initialize(URL location, ResourceBundle resources) {

        for (int n = 0; n < sala.getMaxfila(); n++) {
            for (int m = 0; m < sala.getMaxcolum(); m++) {
                int a = 13 + ((m / 2) - (n % 2)) * (~m);
                Pane filan = (Pane) butacas.getChildren().get(n);

                Button asientoa = (Button) filan.getChildren().get(a);
                asientoa.setStyle("fx-background-color:  #FFFFFF");
                asientoa.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent click) {
                        this.comprar(asientoa.getId());
                    }
                    private void comprar(String id) {
                        BuyTicket.this.comprar(id);
                    }
                });
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
            button.setStyle("fx-opacity: 0.8");
            button.setStyle("fx-background-color:  #3ED715");

        }
    }


}
