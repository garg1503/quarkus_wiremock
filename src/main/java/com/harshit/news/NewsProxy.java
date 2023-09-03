package com.harshit.news;

import com.harshit.news.News;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "https://smart-city-app.onrender.com/api")
public interface NewsProxy {

    @GET
    @Path("/news/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getNewsById(@PathParam("id") int id);

    @POST
    @Path("/newNews")
    @Consumes(MediaType.APPLICATION_JSON)
    Response createNews(News news);

    @GET
    @Path("/news")
    @Produces(MediaType.APPLICATION_JSON)
    List<News> getAllNews();

}
