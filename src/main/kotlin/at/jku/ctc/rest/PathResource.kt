package at.jku.ctc.rest

import at.jku.ctc.business.PathFacade
import at.jku.ctc.entity.ShortestPath
import javax.inject.Inject
import javax.json.JsonObject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("path")
open class PathResource {

    @Inject
    private lateinit var pathFacade: PathFacade

    @POST
    @Path("/maintenance")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    open fun createMaintenancePath(obj: JsonObject): Response {
        return Response.accepted(pathFacade.createMaintenancePath(obj)).build()
    }

    @POST
    @Path("/blocked")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    open fun createBlockedPath(obj: JsonObject): Response {
        return Response.accepted(pathFacade.createBlockedPath(obj)).build()
    }

    @GET
    @Path("/shortest")
    @Produces(MediaType.APPLICATION_JSON)
    open fun getShortestPath(@QueryParam("from") from: String, @QueryParam("to") to: String,
                             @QueryParam("avoidance") @DefaultValue("false") avoidance: Boolean,
                             @QueryParam("priority") @DefaultValue("Lowest") priority: String):
            Response {
        val path: ShortestPath? = if(from.toLongOrNull() != null && to.toLongOrNull() != null) {
            pathFacade.findShortestPath(from.toLong(), to.toLong(), avoidance, priority)
        } else {
            pathFacade.findShortestPath(from, to, avoidance, priority)
        }
        return if (path == null) {
            if(avoidance) {
                Response.status(Response.Status.BAD_REQUEST).build()
            } else {
                Response.status(Response.Status.BAD_REQUEST).build()
            }
        } else {
            Response.ok(path).build()
        }
    }

}