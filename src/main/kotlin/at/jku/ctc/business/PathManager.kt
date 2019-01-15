package at.jku.ctc.business

import at.jku.ctc.algorithm.IShortestPath
import at.jku.ctc.entity.*
import java.time.LocalDateTime
import javax.enterprise.context.RequestScoped
import javax.inject.Inject

@RequestScoped
open class PathManager {

    @Inject
    private lateinit var shortestPath: IShortestPath
    @Inject
    private lateinit var directionFacade: DirectionFacade

    open fun createMaintenancePath(startDate: LocalDateTime, endDate: LocalDateTime,
                              vararg streets: Street): MaintenancePath {
        val start = directionFacade.storeDirection(Direction(street = streets[0]))
        val maintenancePath = MaintenancePath(start = start, blockadeStart = startDate, maintenanceFinish =  endDate)
        streets.forEachIndexed { index, street ->
            street.takeIf { index > 0 }?.apply { constructPath(maintenancePath, street) }
        }
        return maintenancePath
    }

    open fun createBlockedPath(startDate: LocalDateTime, blockadeType: BlockadeType, priorityType: PriorityType,
                          vararg streets: Street): BlockedPath {
        val start = directionFacade.storeDirection(Direction(street = streets[0]))
        val blockedPath = BlockedPath(start = start, blockadeStart = startDate, blockadeType = blockadeType,
                priorityToAvoid = priorityType)
        streets.forEachIndexed { index, street ->
            street.takeIf { index > 0 }?.apply { constructPath(blockedPath, street) }
        }
        return blockedPath
    }

    open fun constructPath(path: Path, street: Street): Path {
        if(path is BlockedPath) {
            val direction = directionFacade.storeDirection(Direction(street = street))
            path.addDirection(direction)
        } else {
            path.addDirection(Direction(street = street))
        }
        return path
    }

    open fun findShortestPath(startStreet: Street, endStreet: Street): ShortestPath {
        return shortestPath.findShortestPath(startStreet, endStreet)
    }

    open fun findShortestPath(startStreet: Street, endStreet: Street, priorityType: PriorityType): ShortestPath {
        return shortestPath.findShortestPath(startStreet, endStreet, true, priorityType)
    }

}