package com.harshit.news;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import java.util.List;

@Path("/news")
public class NewsResource {

    @RestClient
    NewsProxy proxy;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNewsById(@PathParam("id") int id){
        try{
            Response response = proxy.getNewsById(id);
            return Response.ok(response.readEntity(News.class)).build();
        } catch(ResteasyWebApplicationException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build();
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNews(News news){
        Response response = proxy.createNews(news);
        if(response.getStatus() == 200){
            return Response.ok(response.readEntity(News.class)).build();
        }
        else {
            return Response.status(response.getStatus()).entity("Error Occured").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getAllNews(){
        List<News> allNews = proxy.getAllNews();
        return allNews;
    }
}
