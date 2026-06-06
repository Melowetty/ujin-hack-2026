package team.mcqueen.smartdisplay.service

import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.domain.display.DisplayEntity
import team.mcqueen.smartdisplay.domain.emergency.EmergencyEntity
import team.mcqueen.smartdisplay.generated.model.Display
import team.mcqueen.smartdisplay.generated.model.Emergency
import team.mcqueen.smartdisplay.generated.model.TargetType
import team.mcqueen.smartdisplay.repository.EmergencyRepository
import java.time.OffsetDateTime

@Service
class EmergencyService(
    private val emergencyRepository: EmergencyRepository
) {
    fun createEmergency(untilAt: OffsetDateTime, text: String, priority: Long, target: Long, targetType: TargetType): Emergency {
        val emergency = EmergencyEntity(
            untilAt = untilAt,
            text = text,
            priority = priority,
            target = target,
            targetType = targetType 
        )
        val newEmergency = emergencyRepository.save(emergency)
        return Emergency(
            id = newEmergency.id,
            untilAt = newEmergency.untilAt,
            text = newEmergency.text,
            priority = newEmergency.priority,
            target = newEmergency.target,
            targetType = newEmergency.targetType
        )
    }

    fun deleteEmergency(emergencyId: Long) {
        emergencyRepository.deleteById(emergencyId)
    }
}