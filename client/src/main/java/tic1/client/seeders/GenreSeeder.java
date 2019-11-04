package tic1.client.seeders;

import tic1.client.models.Genre;
import tic1.client.services.GenreRestTemplate;

public class GenreSeeder {
    public static void main(String[] args) {
        GenreRestTemplate genreRestTemplate = new GenreRestTemplate();
        Genre genre = new Genre();
        genre.setGenre("Accion");
        genreRestTemplate.createGenre(genre);

        genre = new Genre();
        genre.setGenre("Drama");
        genreRestTemplate.createGenre(genre);

        genre = new Genre();
        genre.setGenre("Fantasia");
        genreRestTemplate.createGenre(genre);

        genre = new Genre();
        genre.setGenre("Terror");
        genreRestTemplate.createGenre(genre);

        genre = new Genre();
        genre.setGenre("Comedia");
        genreRestTemplate.createGenre(genre);

        genre = new Genre();
        genre.setGenre("Romantica");
        genreRestTemplate.createGenre(genre);
    }
}
