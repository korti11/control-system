package at.jku.ctc.entity

import javax.persistence.*

@Entity
@NamedQueries(
        NamedQuery(name = "Street.FindByName", query = "select s from Street s where s.name = :NAME")
)
data class Street(@field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = -1,
                  val name: String = "", val length: Int = -1)