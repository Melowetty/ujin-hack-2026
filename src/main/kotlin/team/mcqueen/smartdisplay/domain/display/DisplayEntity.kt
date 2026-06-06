package team.mcqueen.smartdisplay.domain.display

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Column
import jakarta.persistence.GenerationType


@Entity
@Table(name = "display")
class DisplayEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0,

    @Column
    var name: String,

    @Column(nullable = true)
    var templateId: Long?,

    @Column
    var houseId: Long,

    @Column(nullable = true)
    var floor: Int?,

    @Column(nullable = true)
    var entrance: Int?
)
