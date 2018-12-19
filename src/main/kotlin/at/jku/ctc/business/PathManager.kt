package at.jku.ctc.business

import at.jku.ctc.algorithm.IShortestPath
import at.jku.ctc.entity.*
import java.time.LocalDateTime
import javax.ejb.Stateless
import javax.inject.Inject

@Stateless
open class PathManager {

    @Inject
    private lateinit var shortestPath: IShortestPath

    fun createMaintenancePath(startDate: LocalDateTime, endDate: LocalDateTime,
                              vararg streets: Street): MaintenancePath {
        val maintenancePath = MaintenancePath(Direction(streets[0]), startDate, endDate)
        streets.forEachIndexed { index, street ->
            street.takeIf { index > 0 }?.apply { constructPath(maintenancePath, street) }
        }
        return maintenancePath
    }

    fun createBlockedPath(startDate: LocalDateTime, blockadeType: BlockadeType, priorityType: PriorityType,
                          vararg streets: Street): BlockedPath {
        val blockedPath = BlockedPath(Direction(streets[0]), startDate, blockadeType, priorityType)
        streets.forEachIndexed { index, street ->
            street.takeIf { index > 0 }?.apply { constructPath(blockedPath, street) }
        }
        return blockedPath
    }

    fun constructPath(path: Path, street: Street): Path {
        path.addDirection(street)
        return path
    }

    fun findShortestPath(startAddress: Address, endAddress: Address): ShortestPath {
        return shortestPath.findShortestPath(startAddress, endAddress, PriorityType.Lowest)
    }

    fun findShortestPath(startAddress: Address, endAddress: Address, priorityType: PriorityType): ShortestPath {
        TODO("Implement the algorithm to find the shortest path with path avoidance from point A to point B")
    }

    fun checkForPathAvoidency(path: Path, priorityType: PriorityType): Boolean {
        TODO("Implement the algorithm to check if it should avoid the path")
    }

}