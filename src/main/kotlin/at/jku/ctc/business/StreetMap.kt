package at.jku.ctc.business

import at.jku.ctc.entity.BlockedPath
import at.jku.ctc.entity.Path
import at.jku.ctc.entity.PriorityType
import at.jku.ctc.entity.Street
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
open class StreetMap(private val mapCache: MutableMap<Street, Array<Street>> = mutableMapOf(),
                private val blockedPathsCache: MutableList<BlockedPath> = mutableListOf()) {

    @Inject
    private lateinit var streetFacade: StreetFacade
    @Inject
    private lateinit var pathFacade: PathFacade

    open fun getStreetNeighbors(street: Street): Array<Street> {
        return mapCache.getOrPut(street) {
            streetFacade.getNeighbors(street.id).toTypedArray()
        }
    }

    open fun isPathBlockedForPriority(path: Path, priorityType: PriorityType): Boolean {
        for (blockedPath in blockedPathsCache) {
            if (blockedPath.containsPath(path) && blockedPath.priorityToAvoid.ordinal >= priorityType.ordinal) {
                return true
            }
        }
        while (pathFacade.hasNewBlockedPaths(blockedPathsCache.size)) {
            val newEntries = pathFacade.getBlockesPaths(blockedPathsCache.size, 10)
            for (blockedPath in newEntries) {
                if (blockedPath.containsPath(path) && blockedPath.priorityToAvoid.ordinal >= priorityType.ordinal) {
                    blockedPathsCache.addAll(newEntries)
                    return true
                }
            }
            blockedPathsCache.addAll(newEntries)
        }
        return false
    }

}