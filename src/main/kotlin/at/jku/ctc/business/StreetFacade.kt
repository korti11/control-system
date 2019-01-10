package at.jku.ctc.business

import at.jku.ctc.entity.Street
import at.jku.ctc.entity.StreetNeighbor
import javax.enterprise.context.RequestScoped
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@RequestScoped
open class StreetFacade {

    @PersistenceContext
    private lateinit var em: EntityManager

    open fun getById(id: Long): Street? {
        return em.find(Street::class.java, id)
    }

    open fun getByName(name: String): Street {
        return em.createNamedQuery("Street.FindByName", Street::class.java).setParameter("NAME", name).singleResult
    }

    open fun getNeighbors(id: Long): List<Street> {
        return em.createNamedQuery("StreetNeighbor.GetNeighbors", StreetNeighbor::class.java)
                .setParameter("ID", id).resultList.map { it.neighbor }
    }
}