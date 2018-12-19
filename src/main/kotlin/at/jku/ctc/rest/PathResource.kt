package at.jku.ctc.rest

import at.jku.ctc.business.PathFacade
import javax.inject.Inject
import javax.json.JsonObject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("path")
class PathResource {

    @Inject
    private lateinit var pathFacade: PathFacade

    @POST
    @Path("/maintenance")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createMaintenancePath(obj: JsonObject): Response {
        return Response.accepted(pathFacade.createMaintenancePath(obj)).build()
    }

    @POST
    @Path("/blocked")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createBlockedPath(obj: JsonObject): Response {
        return Response.accepted(pathFacade.createBlockedPath(obj)).build()
    }

    @GET
    @Path("/shortest")
    @Produces(MediaType.APPLICATION_JSON)
    fun getShortestPath(@QueryParam("from") from: String, @QueryParam("to") to: String,
                        @QueryParam("avoidance") avoidance: Boolean = false,
                        @QueryParam("priority") priority: String = "Lowest"):
            Response {
        if(from.toLongOrNull() != null && to.toLongOrNull() != null) {
            return Response.ok(pathFacade.findShortestPath(from.toLong(), to.toLong(), avoidance, priority)).build()
        }
        return Response.ok(pathFacade.findShortestPath(from, to, avoidance, priority)).build()
    }

}