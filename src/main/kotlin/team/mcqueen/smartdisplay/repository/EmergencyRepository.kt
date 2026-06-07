package team.mcqueen.smartdisplay.repository

import java.time.OffsetDateTime
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.mcqueen.smartdisplay.domain.display.DisplayEntity
import team.mcqueen.smartdisplay.domain.emergency.EmergencyEntity
import team.mcqueen.smartdisplay.domain.log.LogEntity
import team.mcqueen.smartdisplay.generated.model.Display
import team.mcqueen.smartdisplay.generated.model.Emergency
import java.util.*
import org.springframework.data.jpa.repository.Query
import team.mcqueen.smartdisplay.generated.model.TargetType

@Repository
interface EmergencyRepository : JpaRepository<EmergencyEntity, Long> {
    @Query("SELECT emergency FROM EmergencyEntity emergency" +
            " WHERE emergency.target = :target and emergency.targetType = :targetType and " +
            "emergency.untilAt > CURRENT_TIMESTAMP OR emergency.untilAt IS NULL")
    fun getEmergencyByTargetAndTargetTypeAndUntilAtMoreThen(target: Long, targetType: TargetType): List<Emergency>
}