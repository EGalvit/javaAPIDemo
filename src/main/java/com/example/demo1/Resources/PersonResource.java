package com.example.demo1.Resources;

import com.example.demo1.DataService.PersonDataService;
import com.example.demo1.models.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
public class PersonResource {
    @GET
    @Produces("application/json")
    public List<Person> allPersonsGET() {
        PersonDataService dataService = new PersonDataService();
        return dataService.getAllPersons();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{personId}")
    public Person onePersonGET(@PathParam("personId") int personId) {
        PersonDataService dataService = new PersonDataService();
        return dataService.getPerson(personId);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public int personPOST(Person person) {
        PersonDataService dataService = new PersonDataService();
        return dataService.addPerson(person);
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public int personPUT(Person person) {
        PersonDataService dataService = new PersonDataService();
        dataService.updatePerson(person);
        return 1;
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{personId}")
    public int personDELETE(@PathParam("personId") int personId) {
        PersonDataService dataService = new PersonDataService();
        return dataService.deletePerson(personId);
    }
}