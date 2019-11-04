package tic1.client.models;

import tic1.commons.transfers.MovieGenreDTO;

public class Genre {
    private long id;
    private String genre;

    public Genre() {
    }

    public Genre(MovieGenreDTO temp) {
        this.genre = temp.getGenre();
        this.id = temp.getId();
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

    public void Genre(MovieGenreDTO temp){
        this.setId(temp.getId());
        this.setGenre(temp.getGenre());
    }
}
