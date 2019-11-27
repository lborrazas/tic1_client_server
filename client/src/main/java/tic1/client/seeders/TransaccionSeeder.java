package tic1.client.seeders;

import tic1.client.models.*;
import tic1.client.services.*;
import tic1.commons.transfers.*;

import javax.security.auth.callback.TextInputCallback;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TransaccionSeeder {

    public static void main(String[] args){
    TransaccionRestTemplate transaccionRestTemplate = new TransaccionRestTemplate();
    FuncionRestTemplate funcionRestTemplate = new FuncionRestTemplate();
    UserRestTemplate userRestTemplate = new UserRestTemplate();

    Transaccion transaccion = new Transaccion();
        TicketRestTemplate ticketRestTemplate = new TicketRestTemplate();
        List<Object> uses = userRestTemplate.findAll();
        boolean booleantemp = true;
        int i = -1;
        UserDTO userDto = new UserDTO();
        UserClient user = new UserClient( userDto);
        while (booleantemp){
            i++;
            if(uses.get(i).getClass().equals(UserClient.class)){
                user= (UserClient) uses.get(i);
                booleantemp = false;
            };
        }
        List<Funcion> funcion = funcionRestTemplate.returnAll();//.get(0).getMovie().getId();
        transaccion.setClient(user.getId());

        List<Ticket> tockets = ticketRestTemplate.findByFunction_dateAndsalaid(funcion.get(0).getDate(), funcion.get(0).getSalaId());
        //transaccionRestTemplate.create(, tockets);
    }
}
