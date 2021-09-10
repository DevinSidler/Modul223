package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/users")
@RolesAllowed({ "User" })
@Tag(name = "Users", description = "Handling of Users")
public class UserController {

    @Inject
    UserService userService;

    /**
     * Gets all Users
     * @return Returns list of all users
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list() {
        return userService.findAll();
    }

    /**
     * creates new user
     * @param user user to create new user
     * @return returns a user
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User add(User user) {
        return userService.createUser(user);
    }

    /**
     * deletes a user
     * @param id id of user to delete
     */
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam Long id){
        userService.deleteUser(id);
    }

    /**
     * gets a single user
     * @param id id of user to get
     * @return returns a user
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getSingleActivity(@PathParam Long id){
        return userService.getUserById(id);
    };

    /**
     * Updates a user
     */
    @PUT
    public void update(User user){
        userService.update(user);
    }

}
