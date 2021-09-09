package ch.zli.m223.punchclock.controller;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.domain.User;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.ViewModel.LoginResultViewModel;
import ch.zli.m223.punchclock.ViewModel.LoginViewModel;
import io.smallrye.jwt.build.Jwt;

import ch.zli.m223.punchclock.service.UserService;

/*
 * Do not use in productive environments!
 */

@Tag(name = "Authorization", description = "Sample to manage Authorization")
@Path("/auth")
public class AuthentificationController {

    @Inject
    UserService userService;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginResultViewModel login(LoginViewModel loginViewModel){

        User user = userService.getUserByEmailPassword(loginViewModel.getEmail(), loginViewModel.getPassword());

        if(loginViewModel.getEmail().equals(user.getEmail()) && loginViewModel.getPassword().equals(user.getPassword())){
            String token =
                    Jwt.issuer("https://zli.ch/issuer")
                            .upn("user@zli.ch")
                            .groups(new HashSet<>(Arrays.asList("User")))
                            .expiresIn(Duration.ofHours(1))
                            .sign();
            return new LoginResultViewModel(token);
        }
        throw new NotAuthorizedException("User ["+loginViewModel.getEmail()+"] not known");
    }

    @POST
    @Path("/signUp")
    @Consumes(MediaType.APPLICATION_JSON)
    public void signUp(User user){

        userService.createUser(user);

    }
}

