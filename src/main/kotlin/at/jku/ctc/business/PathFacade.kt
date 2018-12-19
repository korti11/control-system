package at.jku.ctc.business

import at.jku.ctc.entity.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.ejb.Stateless
import javax.inject.Inject
import javax.json.JsonObject

@Stateless
class PathFacade {

    @Inject
    private lateinit var pathManager: PathManager
    @Inject
    private lateinit var streetFacade: StreetFacade

    fun createMaintenancePath(obj: JsonObject): MaintenancePath {
        val startDate = LocalDateTime.parse(obj.getString("startDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val endDate = LocalDateTime.parse(obj.getString("endDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val streets = obj.getJsonArray("streets").map { streetFacade.getByName(it.toString()) }.toTypedArray()
        return pathManager.createMaintenancePath(startDate, endDate, *streets)
    }

    fun createBlockedPath(obj: JsonObject): BlockedPath {
        val startDate = LocalDateTime.parse(obj.getString("startDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val blockadeType = BlockadeType.valueOf(obj.getString("blockadeType"))
        val priorityType = PriorityType.valueOf(obj.getString("priority"))
        val streets = obj.getJsonArray("streets").map { streetFacade.getByName(it.toString()) }.toTypedArray()
        return pathManager.createBlockedPath(startDate, blockadeType, priorityType, *streets)
    }

    fun findShortestPath(from: String, to: String, avoidance: Boolean, priority: String): ShortestPath {
        return findShortestPath(streetFacade.getByName(from), streetFacade.getByName(to),
                avoidance, PriorityType.valueOf(priority))
    }

    fun findShortestPath(from: Long, to: Long, avoidance: Boolean, priority: String): ShortestPath {
        return findShortestPath(streetFacade.getById(from), streetFacade.getById(to),
                avoidance, PriorityType.valueOf(priority))
    }

    private fun findShortestPath(from: Street, to: Street, avoidance: Boolean, priorityType: PriorityType):
            ShortestPath {
        if (avoidance) {
            return pathManager.findShortestPath(Address(street = from), Address(street = to))
        } else {
            return pathManager.findShortestPath(Address(street = from), Address(street = to), priorityType)
        }
    }
}