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
//@RolesAllowed({ "User" })
@Tag(name = "Users", description = "Handling of Users")
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list() {
        return userService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User add(User user) {
        return userService.createUser(user);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam Long id){
        userService.deleteUser(id);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getSingleActivity(@PathParam Long id){
        return userService.getUserById(id);
    };

    @PUT
    public void update(User user){
        userService.update(user);
    }

}
