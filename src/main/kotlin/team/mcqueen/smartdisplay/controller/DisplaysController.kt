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
//        return ResponseEntity.ok(Display(
//            id = 1,
//            name = "Display1",
//            templateId = 1,
//            houseId = 1,
//            floor = 1,
//            entrance = 1
//            )
//        )
        return ResponseEntity.ok(displayService.createDisplay(
            displayInput.name,
            displayInput.templateId,
            id,
            displayInput.floor,
            displayInput.entrance
        ))
    }
    override fun deleteDisplay(id: Long, displayId: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok().build()
    }

    override fun getDisplayById(id: Long, displayId: Long): ResponseEntity<Display> {
        return ResponseEntity.ok(Display(
            id = 1,
            name = "Display1",
            templateId = 1,
            houseId = 1,
            floor = 1,
            entrance = 1
        )
        )
    }

    override fun getDisplays(id: Long): ResponseEntity<List<Display>> {
        return ResponseEntity.ok(listOf(
            Display(
            id = 1,
            name = "Display1",
            templateId = 1,
            houseId = 1,
            floor = 1,
            entrance = 1
        ),
            Display(
                id = 2,
                name = "Display2",
                templateId = 1,
                houseId = 1,
                floor = 2,
                entrance = 1)
        )
        )
    }

    override fun updateDisplay(id: Long, displayId: Long, displayInput: DisplayInput): ResponseEntity<Display> {
        return ResponseEntity.ok(Display(
            id = 1,
            name = "Display1 edited",
            templateId = 2,
            houseId = 1,
            floor = 1,
            entrance = 1
        )
        )
    }
}