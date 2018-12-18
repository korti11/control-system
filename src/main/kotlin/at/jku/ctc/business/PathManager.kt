package at.jku.ctc.business

import at.jku.ctc.entity.*
import java.time.LocalDateTime
import javax.ejb.Stateless

@Stateless
open class PathManager {

    fun createMaintenancePath(startDate: LocalDateTime, endDate: LocalDateTime,
                              vararg streets: Street): MaintenancePath {
        TODO("Implement the creation of an maintenance path")
    }

    fun createBlockedPath(startDate: LocalDateTime, blockadeType: BlockadeType, vararg streets: Street): BlockedPath {
        TODO("Implement the creation of an blocked path")
    }

    fun constructPath(path: Path, street: Street): Path {
        TODO("Implement the construction of a path")
    }

    fun findShortestPath(startAddress: Address, endAddress: Address): ShortestPath {
        TODO("Implement the algorithm to find the shortest path from point A to point B")
    }

    fun findShortestPath(startAddress: Address, endAddress: Address, priorityType: PriorityType): ShortestPath {
        TODO("Implement the algorithm to find the shortest path with path avoidance from point A to point B")
    }

    fun checkForPathAvoidency(path: Path, priorityType: PriorityType): Boolean {
        TODO("Implement the algorithm to check if it should avoid the path")
    }

}