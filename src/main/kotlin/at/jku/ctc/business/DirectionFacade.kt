package at.jku.ctc.business

import at.jku.ctc.entity.Direction
import javax.enterprise.context.RequestScoped
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@RequestScoped
open class DirectionFacade {

    @PersistenceContext
    private lateinit var em: EntityManager

    @Transactional
    open fun storeDirection(direction: Direction): Direction {
        return em.merge(direction)
    }

}