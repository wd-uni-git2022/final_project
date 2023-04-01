package htw.webdev.max.controller;

import htw.webdev.max.model.AppUser;
import htw.webdev.max.service.UserService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/user")
public class UserController {


    @Inject
    UserService userService;

    @GET
    @RolesAllowed("Admin")
    public Response getAllUsers(){
        List<AppUser> userList = userService.getAllUsers();
        return Response.ok(userList).build();
    }

    @DELETE
    @RolesAllowed("Admin")
    @Path("/{userId}")
    public Response deleteUser(@PathParam long userId){
        boolean result = userService.deleteUser(userId);
        if(result){
            return Response.ok().build();
        }else{
            return Response.status(400).build();
        }
    }

    @PATCH
    @RolesAllowed("Admin")
    @Path("/{userId}")
    public Response changeUsername(@PathParam long userId, AppUser appUser){
        boolean result = userService.changeUsername(userId, appUser.getUsername());
        if(result){
            return Response.ok().build();
        }else{
            return Response.status(400).build();
        }
    }
}
