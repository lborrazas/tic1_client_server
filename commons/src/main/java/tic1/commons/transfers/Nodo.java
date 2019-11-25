package tic1.commons.transfers;

import java.util.ArrayList;

public class Nodo {
    private ArrayList<TicketDTO> ticketDTOS;
    private  TransaccionDTO transaccionDTO;

    public Nodo() {
    }

    public ArrayList<TicketDTO> getTicketDTOS() {
        return ticketDTOS;
    }

    public void setTicketDTOS(ArrayList<TicketDTO> ticketDTOS) {
        this.ticketDTOS = ticketDTOS;
    }

    public TransaccionDTO getTransaccionDTO() {
        return transaccionDTO;
    }

    public void setTransaccionDTO(TransaccionDTO transaccionDTO) {
        this.transaccionDTO = transaccionDTO;
    }
}
