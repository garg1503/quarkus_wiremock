//package com.harshit;
//
//import jakarta.ws.rs.core.Response;
//import jakarta.ws.rs.ext.ExceptionMapper;
//import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;
//
//public class HttpError implements ExceptionMapper<ResteasyWebApplicationException> {
//    @Override
//    public Response toResponse(ResteasyWebApplicationException exception) {
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build();
//    }
//}
