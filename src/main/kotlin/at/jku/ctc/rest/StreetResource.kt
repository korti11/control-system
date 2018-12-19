package at.jku.ctc.rest

import at.jku.ctc.business.StreetFacade
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("street")
open class StreetResource {

    @Inject
    lateinit var streetFacade: StreetFacade

    @GET
    @Path("{streetID}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getStreet(@PathParam("streetID") id: Long): Response {
        return Response.ok(streetFacade.getById(id)).build()
    }

    @GET
    @Path("{streetName}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getStreet(@PathParam("streetName") name: String): Response {
        return Response.ok(streetFacade.getByName(name)).build()
    }

    @GET
    @PathParam("{streetID}/neighbors")
    @Produces(MediaType.APPLICATION_JSON)
    fun getStreetNeighbors(@PathParam("streetID") id: Long): Response {
        return Response.ok(streetFacade.getNeighbors(id)).build()
    }

}