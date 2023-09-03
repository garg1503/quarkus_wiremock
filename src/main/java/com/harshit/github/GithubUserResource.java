package com.harshit.github;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/gitUser")
public class GithubUserResource {

    @RestClient
    GithubUserNameProxy proxy;

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByName(@PathParam("name") String name){
        Response response = proxy.getUserByName(name);
        if(response.getStatus()==200){
            return response.ok(response.readEntity(GithubUser.class)).build();
        }
        else{
            return Response.status(response.getStatus()).entity("Error Occured").build();
        }
    }
}