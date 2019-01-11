package at.jku.ctc.business

import at.jku.ctc.entity.BlockedPath
import at.jku.ctc.entity.Path
import at.jku.ctc.entity.PriorityType
import at.jku.ctc.entity.Street
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
open class StreetMap(private val mapCache: MutableMap<Street, Array<Street>> = mutableMapOf(),
                private val blockedPathsCache: Array<BlockedPath> = emptyArray()) {

    @Inject
    private lateinit var streetFacade: StreetFacade

    open fun getStreetNeighbors(street: Street): Array<Street> {
        return mapCache.getOrPut(street) {
            streetFacade.getNeighbors(street.id).toTypedArray()
        }
    }

    open fun isPathBlockedForPriority(path: Path, priorityType: PriorityType): Boolean {
        TODO("Check if part of the path is in cache or check for the endpoint")
    }

}