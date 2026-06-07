package team.mcqueen.smartdisplay.service

import jakarta.transaction.Transactional
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.domain.display.DisplayEntity
import team.mcqueen.smartdisplay.exception.NotFoundException
import team.mcqueen.smartdisplay.generated.model.Display
import team.mcqueen.smartdisplay.repository.DisplayRepository

@Service
class DisplayService(
    private val displayRepository: DisplayRepository,
    private val logService: LogService
) {
    fun createDisplay(name: String, templateId: Long?, houseId:Long, floor: Int?, entrance: Int?): Display {
        var token = RandomStringUtils.randomAlphanumeric(8)
        val display = DisplayEntity(
            name = name,
            token = token,
            templateId = templateId,
            houseId = houseId,
            floor = floor,
            entrance = entrance
        )
        val newDisplay = displayRepository.save(display)
        logService.createLog(1,newDisplay.id,"actor ${1} create new display ${newDisplay.id}")
        return Display(
            newDisplay.id,
            newDisplay.token,
            newDisplay.name,
            newDisplay.houseId,
            newDisplay.templateId,
            newDisplay.floor,
            newDisplay.entrance
        )
    }

    fun deleteDisplay(displayId: Long) {
        displayRepository.deleteById(displayId)
    }

    fun getDisplayById(displayId: Long): Display {
        var entity = displayRepository.getDisplayEntityById(displayId)
            ?: throw NotFoundException("Display")
        return Display(
            id = entity.id,
            token = entity.token,
            name = entity.name,
            houseId = entity.houseId,
            templateId = entity.templateId,
            floor = entity.floor,
            entrance = entity.entrance
        )
    }

    fun getHouseDisplays(houseId: Long): List<Display> {
        var entities = displayRepository.getAllByHouseId(houseId)
            ?: throw NotFoundException("Logs")
        return entities.map { entity -> Display(
            id = entity.id,
            token = entity.token,
            name = entity.name,
            houseId = entity.houseId,
            templateId = entity.templateId,
            floor = entity.floor,
            entrance = entity.entrance
        ) }
    }

    @Transactional
    fun updateDisplay(displayId: Long, name: String, templateId: Long?, houseId:Long, floor: Int?, entrance: Int?): Display {
        var entity = displayRepository.getDisplayEntityById(displayId)
            ?: throw NotFoundException("Display")
        entity.name = name
        entity.houseId = houseId
        entity.floor = floor
        entity.entrance = entrance
        entity.templateId = templateId
        var newEntity = displayRepository.save(entity)
        logService.createLog(1,entity.id,"actor ${1} edit display ${entity.id}")
        return Display(
            id = newEntity.id,
            token = entity.token,
            name = newEntity.name,
            houseId = newEntity.houseId,
            templateId = newEntity.templateId,
            floor = newEntity.floor,
            entrance = newEntity.entrance
        )
    }

}