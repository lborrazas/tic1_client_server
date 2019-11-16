package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.SalaDTO;
import tic1.server.business.CinemaMgr;
import tic1.server.business.SalaMgr;
import tic1.server.entities.Sala;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SalaRestController {

    @Autowired
    SalaMgr salaMgr;
    @Autowired
    CinemaMgr cinemaMgr;
    private Sala salaFromDto(SalaDTO salaDTO){
    Sala sala= new Sala();
        sala.setCinema(cinemaMgr.getOne(salaDTO.getCinemaid()));
        sala.setId(salaDTO.getId());
        sala.setName(salaDTO.getName());
        return sala;
    }


    @PostMapping("/sala")
    public void save(@RequestBody SalaDTO salaDTO){

        Sala sala = salaFromDto(salaDTO);
        salaMgr.addSala(sala);
    }

    @DeleteMapping("/sala/{id}")
    public void delete(@PathVariable long id){
        salaMgr.delateById(id);
    }

    @GetMapping("/sala/{id}")
    public  SalaDTO getOne(@RequestBody long id){
        return  salaMgr.getSalaById(id).toDTO();
    }
    @GetMapping("/sala/{name}")
    public List<SalaDTO> getByName(@RequestBody String name){
        List<SalaDTO> salaDTOS = new ArrayList<>();
        for (Sala sala: salaMgr.getByName(name)){
            salaDTOS.add(sala.toDTO());
        }
        return  salaDTOS;
    }
    @GetMapping("/sala/{cinema_id}")
    public List<SalaDTO> getByCinemaName(@RequestBody long cinema_id ) {
        List<SalaDTO> salaDTOS = new ArrayList<>();
        for( Sala salatemp:salaMgr.findAllByCinema(cinemaMgr.getOne(cinema_id))){
            salaDTOS.add(salatemp.toDTO())   ;
        }
        return  salaDTOS;
    }
}
