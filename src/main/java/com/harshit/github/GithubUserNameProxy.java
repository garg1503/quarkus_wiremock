package com.harshit.github;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://api.github.com")
@Produces(MediaType.APPLICATION_JSON)
public interface GithubUserNameProxy {

    @GET
    @Path("/users/{name}")
    Response getUserByName(@PathParam("name") String name);
}
