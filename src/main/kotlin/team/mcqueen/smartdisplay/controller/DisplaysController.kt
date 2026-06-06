package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import team.mcqueen.smartdisplay.generated.api.DisplaysApi
import team.mcqueen.smartdisplay.generated.model.Display
import team.mcqueen.smartdisplay.generated.model.DisplayInput
import team.mcqueen.smartdisplay.service.DisplayService

@RestController
class DisplaysController(
    private val displayService: DisplayService
) : DisplaysApi {
    override fun createDisplay(id: Long, displayInput: DisplayInput): ResponseEntity<Display> {
        return ResponseEntity.ok(displayService.createDisplay(
            displayInput.name,
            displayInput.templateId,
            id,
            displayInput.floor,
            displayInput.entrance
        ))
    }
    override fun deleteDisplay(id: Long, displayId: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok(displayService.deleteDisplay(displayId))
    }

    override fun getDisplayById(id: Long, displayId: Long): ResponseEntity<Display> {
        return ResponseEntity.ok(displayService.getDisplayById(displayId))
    }

    override fun getDisplays(id: Long): ResponseEntity<List<Display>> {
        return ResponseEntity.ok(displayService.getHouseDisplays(id))
    }

    override fun updateDisplay(id: Long, displayId: Long, displayInput: DisplayInput): ResponseEntity<Display> {
        return ResponseEntity.ok(displayService.updateDisplay(
            displayId,
            displayInput.name,
            displayInput.templateId,
            id,
            displayInput.floor,
            displayInput.entrance
        ))
    }
}