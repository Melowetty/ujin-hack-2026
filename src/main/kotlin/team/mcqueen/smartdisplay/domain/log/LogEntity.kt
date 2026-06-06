package team.mcqueen.smartdisplay.domain.log

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Column
import jakarta.persistence.GenerationType

@Entity
@Table(name = "log")
class LogEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    @Column
    var actor: Long,

    @Column
    var displayId: Long,

    @Column
    var text: String
)
