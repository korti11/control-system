package at.jku.ctc.business

import at.jku.ctc.entity.Street
import at.jku.ctc.entity.StreetNeighbor
import javax.ejb.Stateless
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Stateless
open class StreetFacade {

    @PersistenceContext
    lateinit var em: EntityManager

    fun getById(id: Long): Street {
        return em.find(Street::class.java, id)
    }

    fun getByName(name: String): Street {
        return em.createNamedQuery("Street.FindByName", Street::class.java).setParameter("NAME", name).singleResult
    }

    fun getNeighbors(id: Long): List<Street> {
        return em.createNamedQuery("StreetNeighbor.GetNeighbors", StreetNeighbor::class.java)
                .setParameter("ID", id).resultList.map { it.neighbor }
    }
}