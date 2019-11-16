package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.TransaccionDTO;
import tic1.server.business.TransaccionMgr;
import tic1.server.business.UserMgr;
import tic1.server.entities.Transaccion;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TransaccionRestController {
@Autowired
    TransaccionMgr transaccionMgr;
@Autowired
    UserMgr userMgr;

    public Transaccion transaccionFromDTO(TransaccionDTO transaccionDTO){
        Transaccion transaccion = new Transaccion();
        transaccion.setId(transaccionDTO.getId());
        transaccion.setPrecioTotal(transaccionDTO.getPrecioTotal());
        transaccion.setClient(userMgr.getOne(transaccionDTO.getClient()));
        return transaccion;
    }
    @PostMapping("/transaccion")
    public void save(@RequestBody TransaccionDTO transaccionDTO)
    { transaccionMgr.addTransaccion(transaccionFromDTO(transaccionDTO));

    }
    @DeleteMapping("/transaccion/{id}")
    public void delate(@PathVariable("id") long id){
        transaccionMgr.deleteTransaccion(id);
    }

    @GetMapping("/transaccion/{id}")
    public TransaccionDTO getOne(@PathVariable("id") long id){
        return transaccionMgr.getOne(id).toDTO();
    }
    @GetMapping("/transaccion/{name}")
    public List<TransaccionDTO> getByClientName(@PathVariable("name") String name ){
        List<TransaccionDTO> transaccionDTOS= new ArrayList<>();
        for(Transaccion transaccion:transaccionMgr.getByClintUsername(name)){
            transaccionDTOS.add(transaccion.toDTO());
        }
        return transaccionDTOS;

    }

    @GetMapping("/transaccion/{client_id}")
    public List<TransaccionDTO> getByClientID(@RequestBody long client_id ){
        List<TransaccionDTO> transaccionDTOS= new ArrayList<>();
        for(Transaccion transaccion:transaccionMgr.getByClintId(client_id)){
            transaccionDTOS.add(transaccion.toDTO());
        }
        return transaccionDTOS;
    }


}
