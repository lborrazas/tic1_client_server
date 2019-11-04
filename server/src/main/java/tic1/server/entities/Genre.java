package tic1.server.entities;

import tic1.commons.transfers.MovieGenreDTO;

import javax.persistence.*;

@Entity
@Table
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String genre;

    public Genre() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public MovieGenreDTO toDTO(){
        MovieGenreDTO tempDTO = new MovieGenreDTO();
        tempDTO.setGenre(this.genre);
        tempDTO.setId(this.id);
        return tempDTO;
    }

    public Genre(MovieGenreDTO tempDTO){
        this.genre = tempDTO.getGenre();
        this.id = tempDTO.getId();

    }
}
