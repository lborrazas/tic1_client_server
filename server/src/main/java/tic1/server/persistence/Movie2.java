package tic1.server.entities;

import tic1.commons.transfers.MovieDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie2 {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    public Movie2(long id, String name, String description, String duration, boolean esPelicula, List<Actors> elenco) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.esPelicula = esPelicula;
        this.elenco = elenco;
    }

    public Movie2() {
    }

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private boolean esPelicula;

    @Column(name = "")
    private int rating;

    @Column(name = "clasificacion")
    private String clasificacion;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "genero_id")
    private List<Genero> genero;

    @ManyToMany(targetEntity = Actors.class,cascade = CascadeType.ALL)
    @JoinTable(name = "Elenco")
    private List<Actors> elenco;

    private String actors;

/*    public Movie(MovieDTO temp)  {

        this.actors = temp.getActors();
        this.description = temp.getDescription();
        this.duration = temp.getDuration();
        this.id = temp.getId();
        this.name = temp.getName();

    }

 @Lob
    @Column(name = "imagen_Cartelera",columnDefinition = "largeLob")
    private Byte[] imageCartelera;

    public Byte[] getImageCartelera() {
        return imageCartelera;
    }

    public void setImageCartelera(Byte[] imageCartelera) {
        this.imageCartelera = imageCartelera;
    }*/

    public boolean isEsPelicula() {
        return esPelicula;
    }

    public void setEsPelicula(boolean esPelicula) {
        this.esPelicula = esPelicula;
    }

    public List<Actors> getElenco() {
        return elenco;
    }

    public void setElenco(List<Actors> elenco) {
        this.elenco = elenco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

   /* public MovieDTO toDTO() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setActors(this.actors);
        movieDTO.setDescription(this.description);
        movieDTO.setDuration(this.duration);
        movieDTO.setName(this.name);
        movieDTO.setId(this.id);
        return movieDTO;
    }*/

    @Override
    public String toString() {

        return  "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", elenco='" + elenco.toString()  + '\'' +
                ", duration='" + duration + '\'' +
                ", esPelicula='" + esPelicula + '\'' +
                '}';
    }


}


//esto es para guardar una foto o leerla creo que el imput es para guardarla
//File file = new File("");
//Byte[] picInBytes = new Byte[(int) file.length()];
//try(FileInputStream fileInputStream = new FileInputStream(file)){
//fileInputStream.read(picInBytes);}
//