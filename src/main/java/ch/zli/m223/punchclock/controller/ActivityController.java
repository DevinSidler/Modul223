package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.service.ActivityService;
import ch.zli.m223.punchclock.domain.Activity;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/activities")
@RolesAllowed({ "User" })
@Tag(name = "Activities", description = "Handling of activities")
public class ActivityController {

    @Inject
    ActivityService activityService;

    /**
     * Gets all Activities
      * @return Returns List of Activities
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activity> list() {
        return activityService.findAll();
    }

    /**
     * Creates an Activity
     * @param activity Activity to Create an Activity
     * @return returns an activity
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Activity add(Activity activity) {
        return activityService.createActivity(activity);
    }

    /**
     *  Deletes an Activity
     * @param id id of Activity to Delete
     */
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam Long id){
        activityService.deleteActivity(id);
    }

    /**
     * Gets a Single Activity
     * @param id id of Activity to get
     * @return returns an Activity
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Activity getSingleActivity(@PathParam Long id){
        return activityService.getActivityById(id);
    };

    /**
     * Updates an Activity
     * @param activity Activity to override Activity
     */
    @PUT
    public void update(Activity activity){
        activityService.update(activity);
    }

}
