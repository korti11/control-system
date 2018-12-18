package at.jku.ctc.business

import at.jku.ctc.entity.BlockedPath
import at.jku.ctc.entity.Path
import at.jku.ctc.entity.PriorityType
import at.jku.ctc.entity.Street
import javax.ejb.Singleton

@Singleton
class StreetMap(val mapCache: Map<Street, Array<Street>> = mutableMapOf(),
                val blockedPathsCache: Array<BlockedPath> = emptyArray()) {

    fun getStreetNeighbors(street: Street): Array<Street> {
        TODO("If street is in cache load neighbors from cache otherwise get neighbors from the endpoint")
    }

    fun isPathBlockedForPriority(path: Path, priorityType: PriorityType): Boolean {
        TODO("Check if part of the path is in cache or check for the endpoint")
    }

}