package tic1.commons.transfers;

public class MovieDTO {

    private String description;
    private String duration;
    private String actors;
    private String name;


    public MovieDTO() {
    }

    public MovieDTO(String description, String duration, String actors, String name) {
        this.description = description;
        this.duration = duration;
        this.actors = actors;
        this.name = name;

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

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
