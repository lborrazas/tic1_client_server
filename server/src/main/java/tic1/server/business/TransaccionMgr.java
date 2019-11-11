package tic1.server.business;

import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.server.entities.Transaccion;
import tic1.server.persistence.TransaccionRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class TransaccionMgr {
    @Autowired
    private  TransaccionRepository transaccionRepository;


    public List<Transaccion> findAll() {
        return transaccionRepository.findAll();
    }

    public void addTransaccion(Transaccion transaccion) {

        transaccionRepository.save(transaccion);

    }

    public Transaccion getOne(@PathVariable("id") Long id) {
        return transaccionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

    }
    public ResponseEntity<?> deleteTransaccion(@PathVariable("id") Long id) {

        Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        transaccionRepository.delete(transaccion);

        return ResponseEntity.ok().build();
    }


    public void updateTransaccion(@PathVariable("id") Long id, @Valid @RequestBody Transaccion transaccion) {

        Transaccion existingtransaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        existingtransaccion.setClient(transaccion.getClient());
        existingtransaccion.setPrecioTotal(transaccion.getPrecioTotal());
        transaccionRepository.save(existingtransaccion);
    }
    public List<Transaccion> getByClint(Client client){
        return  transaccionRepository.findAllByClient(client);
    }

    public  List<Transaccion> getByClintId(long id){
        return  transaccionRepository.findAllByClientId(id);}

    public  List<Transaccion> getByClintUsername(String username){
        return  transaccionRepository.findAllByClientUsername(username);
    }



}
