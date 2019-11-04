package tic1.commons.transfers;

public class MovieGenreDTO {

    private long id;

    public MovieGenreDTO() {
    }

    private String genre;

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
}
