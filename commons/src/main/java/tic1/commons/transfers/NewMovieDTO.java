package tic1.commons.transfers;
import java.util.List;

public class NewMovieDTO {
        private long id;
        private String description;
        private String duration;
        private List<MovieActorDTO> actors;
        private String name;
        private String genre;


        public NewMovieDTO() {
        }

        public NewMovieDTO(long id, String description, String duration, List<MovieActorDTO> actors, String name, String genre) {
            this.id = id;
            this.description = description;
            this.duration = duration;
            this.actors = actors;
            this.name = name;
            this.genre = genre;

        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

    public List<MovieActorDTO> getActors() {
        return actors;
    }

    public void setActors(List<MovieActorDTO> actors) {
        this.actors = actors;
    }
}
