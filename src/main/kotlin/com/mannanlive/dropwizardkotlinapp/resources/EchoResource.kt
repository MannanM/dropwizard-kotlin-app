package com.mannanlive.dropwizardkotlinapp.resources

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/echo")
@Produces(MediaType.APPLICATION_JSON)
class EchoResource(private val property: String) {

    @GET
    fun get(@QueryParam("query") @DefaultValue("default") queryParam: String) : Any = object {
        val query : String = queryParam
        val prop : String = property
        val number : Long = 123
        val bool : Boolean = false
        val array : List<Int> = listOf(456, 789)
        val map : Map<String, Double> = mapOf(Pair("rate", 11.5))
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        val date : Date = Date(1548930042343)
    }
}