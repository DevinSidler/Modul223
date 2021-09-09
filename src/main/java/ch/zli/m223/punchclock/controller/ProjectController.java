package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.service.ProjectService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/projects")
//@RolesAllowed({ "User" })
@Tag(name = "Projects", description = "Handling of projects")
public class ProjectController {

    @Inject
    ProjectService projectService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> list() {
        return projectService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Project add(Project project) {
        return projectService.createProject(project);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam Long id){
        projectService.deleteProject(id);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getSingleActivity(@PathParam Long id){
        return projectService.getProjectById(id);
    };

    @PUT
    public void update(Project project){
        projectService.update(project);
    }

}
