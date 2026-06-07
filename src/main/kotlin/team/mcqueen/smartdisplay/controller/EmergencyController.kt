package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import team.mcqueen.smartdisplay.generated.api.EmergencyApi
import team.mcqueen.smartdisplay.generated.model.Emergency
import team.mcqueen.smartdisplay.generated.model.EmergencyInput
import team.mcqueen.smartdisplay.generated.model.TargetType
import team.mcqueen.smartdisplay.service.EmergencyService
import java.time.OffsetDateTime

@RestController
class EmergencyController(
    private val emergencyService: EmergencyService
) : EmergencyApi {
    override fun createEmergency(emergencyInput: EmergencyInput): ResponseEntity<Emergency> {
        return ResponseEntity.ok(emergencyService.createEmergency(
            emergencyInput.untilAt,
            emergencyInput.text,
            emergencyInput.priority,
            emergencyInput.target,
            emergencyInput.targetType,
        ))
    }

    override fun deleteEmergency(emergencyId: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok(emergencyService.deleteEmergency(emergencyId))
    }

    override fun getEmergency(): ResponseEntity<List<Emergency>> {
        return ResponseEntity.ok(emergencyService.getEmergencies())
    }
}