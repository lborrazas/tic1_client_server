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
    private
    SalaMgr salaMgr;
    @Autowired
    private
    CinemaMgr cinemaMgr;
    private Sala salaFromDto(SalaDTO salaDTO){
    Sala sala= new Sala();
        sala.setCinema(cinemaMgr.getOne(salaDTO.getCinemaid()));
        sala.setId(salaDTO.getId());
        sala.setName(salaDTO.getName());
        return sala;
    }
    @GetMapping("/sala/")
    public List<SalaDTO> All() {
        List<SalaDTO> cinemasDtos = new ArrayList<>();
        List<Sala> salas = salaMgr.findAll();
        for (Sala room : salas) {
            cinemasDtos.add(room.toDTO()); //
        }
        return cinemasDtos;

    }


    @PostMapping("/sala/{columna}/{fila}")
    public void save(@RequestBody SalaDTO salaDTO,
                     @PathVariable("columna") long maxColumn,@PathVariable("fila") long maxfil){

        Sala sala = salaFromDto(salaDTO);
        sala.setMaxcolum(maxColumn);
        sala.setMaxfila(maxfil);
        salaMgr.addSala(sala);
    }

    @DeleteMapping("/sala/{id}")
    public void delete(@PathVariable String id)
    {
        Integer idint = Integer.valueOf(id);
        salaMgr.delateById((long) idint);
    }

    @GetMapping("/sala/{id}")
    public  SalaDTO getOne(@PathVariable String id){

        int idint = Integer.parseInt(id);


        return salaMgr.getSalaById(idint).toDTO();
    }
    @GetMapping("/sala/name/{name}")
    public List<SalaDTO> getByName(@PathVariable String name){
        List<SalaDTO> salaDTOS = new ArrayList<>();
        for (Sala sala: salaMgr.getByName(name)){
            salaDTOS.add(sala.toDTO());
        }
        return  salaDTOS;
    }
    @GetMapping("/sala/cinema/{cinema_id}")
    public List<SalaDTO> getByCinemaName(@PathVariable long cinema_id ) {
        List<SalaDTO> salaDTOS = new ArrayList<>();
        for( Sala salatemp:salaMgr.findAllByCinema(cinemaMgr.getOne(cinema_id))){
            salaDTOS.add(salatemp.toDTO())   ;
        }
        return  salaDTOS;
    }
}
