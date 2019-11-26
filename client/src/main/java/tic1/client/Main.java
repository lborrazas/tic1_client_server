package tic1.client;

import tic1.client.models.*;
import tic1.client.services.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        MovieRestTemplate movieRestTemplate = new MovieRestTemplate();
        GenreRestTemplate genreRestTemplate = new GenreRestTemplate();
        ActorRestTemplate actorRestTemplate = new ActorRestTemplate();
        UserRestTemplate uRT = new UserRestTemplate();
        ProviderRestTemplate pRT = new ProviderRestTemplate();
        FuncionRestTemplate fRT = new FuncionRestTemplate();
        SalaRestTemplate sRT = new SalaRestTemplate();
        TicketRestTemplate tRT = new TicketRestTemplate();
//        Genre genre = genreRestTemplate.showGenre(6);
//        Genre genre2 = genreRestTemplate.showGenre(9);
//        Actor actor = actorRestTemplate.showActor(1);
//        Actor actor2 = actorRestTemplate.showActor(3);
//        System.out.println(actor2.getId());
//        System.out.println(genre2.getId());
//        Movie movie = new Movie();
//        Set<Actor> actors = new HashSet<>();
//        actors.add(actor);
//        actors.add(actor2);
//        Set<Genre> genres = new HashSet<>();
//        System.out.println(genre.getGenre());
//        genres.add(genre);
//        genres.add(genre2);
//        movie.setName("Salsa");
//        movie.setDescription("lo ke");
//        movie.setActors(actors);
//        movie.setGenre(genres);
//        movie.setDuration(120);


       // movieRestTemplate.createMovie(movie);
      //  ImageRestTemplate imageRestTemplate = new ImageRestTemplate();
      //  imageRestTemplate.showImage("AAA.png");
        //List<Movie> moviesFilter =  movieRestTemplate.filterActorPaged(actor2,0);
       // System.out.println(moviesFilter.get(0).getName());


       // Movie movieForEdit = movies.get(0);
       // System.out.println(movieForEdit.getGenre());
       // movieForEdit.setGenre("NO ANDA");
        //movieForEdit.setName("NOMBRE");
        //System.out.println(movieForEdit.getGenre());



     //   Movie movieFromRepo = movieRestTemplate.showMovie(5);
     //   System.out.println(movieFromRepo.toString());

     //   movieRestTemplate.updateMovie(movieForEdit.getId(),movieForEdit);
       // movieRestTemplate.deleteMovie(3);
       // movieRestTemplate.createMovie(movie);



//        Provider provider = pRT.getByName("juanitos0").get(0);
//        UserManager userManager = new UserManager();
//        userManager.setProvider(provider.getId());
//        userManager.setRole("Gerente");
//        userManager.setUsername("Mate");
//        userManager.setPassword("password");
//        uRT.createUser(userManager);

//        UserAdmin userAdmin = new UserAdmin();
//        userAdmin.setPassword("password");
//        userAdmin.setUsername("juan");
//        uRT.createUser(userAdmin);

//        List<Funcion> funcions =  fRT.findAllByProviderIdPaged(1,0);
//        System.out.println(funcions.get(7).getMovie().getName());

        List<Funcion> funcions =  fRT.findAllbyProvider(46);
        Funcion funcion = funcions.get(0);

//        Sala sala = sRT.getById(45);
//        System.out.println(sala.getName());

        List<Ticket> tickets2 = tRT.findByFunction_dateAndsalaid(funcion.getDate(), funcion.getSalaId());
        System.out.println(tickets2.get(5).getFuncion().getDate());
        Ticket ticket = tickets2.get(0);

        System.out.println(ticket.isBought());

      ticket.setBought(true);

       List<Ticket> ticketsJuanito = new ArrayList<>();
       ticketsJuanito.add(ticket);
//       ticketsJuanito = tickets2.stream().map(ticket1 -> {
//           ticket1.setBought(true);
//           return ticket1;
//       })
      tRT.update(ticketsJuanito);




       // List<Ticket> tickets = tRT.findByFunction_dateAndsalaid()
    }
}
