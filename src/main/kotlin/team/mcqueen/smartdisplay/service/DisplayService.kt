package team.mcqueen.smartdisplay.service

import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.domain.display.DisplayEntity
import team.mcqueen.smartdisplay.generated.model.Display
import team.mcqueen.smartdisplay.repository.DisplayRepository

@Service
class DisplayService(
    private val displayRepository: DisplayRepository
) {
    fun createDisplay(name: String, templateId: Long?, houseId:Long, floor: Int?, entrance: Int?): Display {
        val display = DisplayEntity(
            name = name,
            templateId = templateId,
            houseId = houseId,
            floor = floor,
            entrance = entrance
        )
        val newDisplay = displayRepository.save(display)
        return Display(
            newDisplay.id,
            newDisplay.name,
            newDisplay.houseId,
            newDisplay.templateId,
            newDisplay.floor,
            newDisplay.entrance
        )
    }

}