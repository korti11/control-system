package at.jku.ctc.rest

import at.jku.ctc.business.StreetFacade
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("street")
open class StreetResource {

    @Inject
    private lateinit var streetFacade: StreetFacade

    @GET
    @Path("{streetID}")
    @Produces(MediaType.APPLICATION_JSON)
    open fun getStreet(@PathParam("streetID") id: Long): Response {
        return Response.ok(streetFacade.getById(id)).build()
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    open fun getStreet(@QueryParam("streetName") name: String): Response {
        return Response.ok(streetFacade.getByName(name)).build()
    }

    @GET
    @PathParam("{streetID}/neighbors")
    @Produces(MediaType.APPLICATION_JSON)
    open fun getStreetNeighbors(@PathParam("streetID") id: Long): Response {
        return Response.ok(streetFacade.getNeighbors(id)).build()
    }

}