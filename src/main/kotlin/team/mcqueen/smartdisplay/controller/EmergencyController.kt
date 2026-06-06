package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import team.mcqueen.smartdisplay.generated.api.EmergencyApi
import team.mcqueen.smartdisplay.generated.model.Emergency
import team.mcqueen.smartdisplay.generated.model.EmergencyInput
import team.mcqueen.smartdisplay.generated.model.TargetType
import java.time.OffsetDateTime

class EmergencyController : EmergencyApi {
    override fun createEmergency(emergencyInput: EmergencyInput): ResponseEntity<Emergency> {
        return ResponseEntity.ok(Emergency(
            id = 1,
            untilAt = OffsetDateTime.now(),
            text = "Беспилотная опасность!",
            priority = 1,
            target = 1,
            targetType = TargetType.DISPLAY
        ))
    }
}