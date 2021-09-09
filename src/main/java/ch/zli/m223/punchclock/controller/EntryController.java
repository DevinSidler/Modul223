package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/entries")
@RolesAllowed({ "User" })
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

    @Inject
    EntryService entryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entry> list() {
        return entryService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry add(Entry entry) {
       return entryService.createEntry(entry);
    }

    @DELETE
   @Path("/{id}")
    public void delete(@PathParam Long id){
        entryService.deleteEntry(id);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Entry getSingleEntry(@PathParam Long id){
        return entryService.getEntryById(id);
    };

    @PUT
    public void update(Entry entry){
        entryService.update(entry);
    }

}
