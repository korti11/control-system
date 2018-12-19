package at.jku.ctc.entity

import javax.persistence.*

@Entity
@NamedQuery(name = "StreetNeighbor.GetNeighbors", query = "select n from StreetNeighbor n where n.street.id = :ID")
data class StreetNeighbor(@field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = -1,
                          val street: Street = Street(), val neighbor: Street = Street())