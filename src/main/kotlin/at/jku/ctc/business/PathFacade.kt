package at.jku.ctc.business

import at.jku.ctc.entity.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.json.JsonObject
import javax.json.JsonString
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@RequestScoped
open class PathFacade {

    @PersistenceContext
    private lateinit var em: EntityManager
    @Inject
    private lateinit var pathManager: PathManager
    @Inject
    private lateinit var streetFacade: StreetFacade

    @Transactional
    open fun createMaintenancePath(obj: JsonObject): MaintenancePath {
        val startDate = LocalDateTime.parse(obj.getString("startDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val endDate = LocalDateTime.parse(obj.getString("endDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val streets = obj.getJsonArray("streets").mapNotNull { streetFacade.getByName((it as JsonString).string) }
                .toTypedArray()
        return em.merge(pathManager.createMaintenancePath(startDate, endDate, *streets))
    }

    @Transactional
    open fun createBlockedPath(obj: JsonObject): BlockedPath {
        val startDate = LocalDateTime.parse(obj.getString("startDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val blockadeType = BlockadeType.valueOf(obj.getString("blockadeType"))
        val priorityType = PriorityType.valueOf(obj.getString("priority"))
        val streets = obj.getJsonArray("streets").mapNotNull { streetFacade.getByName((it as JsonString).string) }
                .toTypedArray()
        return em.merge(pathManager.createBlockedPath(startDate, blockadeType, priorityType, *streets))
    }

    open fun getBlockesPaths(first: Int, max: Int): List<BlockedPath> {
        return em.createNamedQuery("BlockedPath.GetAll", BlockedPath::class.java)
                .setFirstResult(first).setMaxResults(max).resultList
    }

    open fun hasNewBlockedPaths(size: Int): Boolean {
        return em.createNamedQuery("BlockedPath.Size").singleResult != size.toLong()
    }

    open fun findShortestPath(from: String, to: String, avoidance: Boolean, priority: String): ShortestPath? {
        return findShortestPath(streetFacade.getByName(from), streetFacade.getByName(to),
                avoidance, PriorityType.valueOf(priority))
    }

    open fun findShortestPath(from: Long, to: Long, avoidance: Boolean, priority: String): ShortestPath? {
        return findShortestPath(streetFacade.getById(from), streetFacade.getById(to),
                avoidance, PriorityType.valueOf(priority))
    }

    private fun findShortestPath(from: Street?, to: Street?, avoidance: Boolean, priorityType: PriorityType):
            ShortestPath? {
        try {
            val start = checkNotNull(from) { "The start street should exist!" }
            val end = checkNotNull(to) { "The end street should exist!" }
            return when (avoidance) {
                true -> pathManager.findShortestPath(start, end, priorityType)
                false -> pathManager.findShortestPath(start, end)
            }
        } catch (e: IllegalStateException) {
            return null
        }
    }
}