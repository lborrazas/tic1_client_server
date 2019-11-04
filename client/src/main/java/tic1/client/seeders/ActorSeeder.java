package tic1.client.seeders;

import tic1.client.models.Actor;
import tic1.client.services.ActorRestTemplate;

public class ActorSeeder {

    public static void main(String[] args){
        ActorRestTemplate actorRestTemplate = new ActorRestTemplate();
        Actor actor = new Actor();
      //  actor.setAge(2013);
      //  actor.setName("Julian Estrasa");
      //  actorRestTemplate.createActor(actor);

         actor = new Actor();
        actor.setAge(1883);
        actor.setName("Matias Aldara");
        actorRestTemplate.createActor(actor);

         actor = new Actor();
        actor.setAge(1994);
        actor.setName("Emilia Campos");
        actorRestTemplate.createActor(actor);

         actorRestTemplate = new ActorRestTemplate();
        actor = new Actor();
        actor.setAge(1992);
        actor.setName("Juliana Aburera");
        actorRestTemplate.createActor(actor);
    }
}
