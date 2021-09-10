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

    /**
     * Gets a List of all Entries
     * @return Returns a List of all Entries
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entry> list() {
        return entryService.findAll();
    }

    /**
     * Create a new Entry
     * @param entry entry to create a new Entry
     * @return returns an entry
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry add(Entry entry) {
       return entryService.createEntry(entry);
    }

    /**
     * Delete an Entry
     * @param id id of Entry to Delete
     */
    @DELETE
   @Path("/{id}")
    public void delete(@PathParam Long id){
        entryService.deleteEntry(id);
    }

    /**
     * Gets a Single Entry
     * @param id id of Entry to get
     * @return returns an Entry
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Entry getSingleEntry(@PathParam Long id){
        return entryService.getEntryById(id);
    };

    /**
     * Updates an Entry
     * @param entry entry to Update Entry
     */
    @PUT
    public void update(Entry entry){
        entryService.update(entry);
    }

}
