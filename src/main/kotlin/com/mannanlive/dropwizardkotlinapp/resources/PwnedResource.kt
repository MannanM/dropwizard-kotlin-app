package com.mannanlive.dropwizardkotlinapp.resources

import com.mannanlive.dropwizardkotlinapp.services.PwnedService
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/pwned")
@Produces(MediaType.APPLICATION_JSON)
class PwnedResource(private val service: PwnedService) {
    @GET
    fun get(@QueryParam("password") @DefaultValue("password") password: String): Response {
        val result = service.getNumberOfTimesPwned(password)
        return if (result.timesPwned > 0) {
            Response.ok(result)
        } else {
            Response.status(404).entity(result)
        }.build()
    }
}
