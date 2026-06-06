package team.mcqueen.smartdisplay.domain.emergency

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Column
import jakarta.persistence.GenerationType
import team.mcqueen.smartdisplay.generated.model.TargetType
import java.time.OffsetDateTime

@Entity
@Table(name = "emergency")
class EmergencyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    @Column
    var text: String,

    @Column
    var target: Long,

    @Column
    var priority: Long,

    @Column
    var targetType: TargetType,

    @Column(nullable = true)
    var untilAt: OffsetDateTime?
)
