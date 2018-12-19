package at.jku.ctc.business

import at.jku.ctc.entity.BlockedPath
import at.jku.ctc.entity.Path
import at.jku.ctc.entity.PriorityType
import at.jku.ctc.entity.Street
import javax.ejb.Singleton
import javax.inject.Inject

@Singleton
class StreetMap(val mapCache: MutableMap<Street, Array<Street>> = mutableMapOf(),
                val blockedPathsCache: Array<BlockedPath> = emptyArray()) {

    @Inject
    lateinit var streetFacade: StreetFacade

    fun getStreetNeighbors(street: Street): Array<Street> {
        return mapCache.getOrPut(street) {
            streetFacade.getNeighbors(street.id).toTypedArray()
        }
    }

    fun isPathBlockedForPriority(path: Path, priorityType: PriorityType): Boolean {
        TODO("Check if part of the path is in cache or check for the endpoint")
    }

}