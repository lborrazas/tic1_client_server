package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tic1.commons.transfers.CinemaDto;
import tic1.server.business.CinemaMgr;
import tic1.server.business.ProviderMgr;
import tic1.server.entities.Cinema;

public class CinemaRestController {
@Autowired
    CinemaMgr cinemaMgr;
@Autowired
    ProviderMgr providerMgr;


    private Cinema cinemaFromDto(CinemaDto cinemaDto){
        Cinema cinema = new Cinema();
        cinema.setProvider(providerMgr.getById(cinemaDto.getProviderId()));
        cinema.setLocation(cinemaDto.getLocation());
        cinema.setId(cinemaDto.getId());
        cinema.setName(cinemaDto.getName());
        return cinema;
    }


    @PostMapping("/cinema")
    public void save(@RequestBody CinemaDto cinemaDto){
        Cinema cinema = cinemaFromDto(cinemaDto);
        cinemaMgr.save(cinema);
    }

    @DeleteMapping("/cinema/{id}")
    public void delete(@PathVariable long id){
        cinemaMgr.deleteCinema(id);
    }
}
