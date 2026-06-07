package team.mcqueen.smartdisplay.service.impl

import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.exception.NotFoundException
import team.mcqueen.smartdisplay.generated.model.House
import team.mcqueen.smartdisplay.repository.DisplayRepository
import team.mcqueen.smartdisplay.repository.TemplateRepository
import team.mcqueen.smartdisplay.service.ComplexService
import team.mcqueen.smartdisplay.service.HouseService
import team.mcqueen.smartdisplay.service.LogService

@Service
class CachedHouseService(
    private val complexService: ComplexService,
    private val displayRepository: DisplayRepository,
    private val templateRepository: TemplateRepository,
    private val logService: LogService
) : HouseService {

    override fun getHouses(complexId: Long): List<House> {
       return complexService.getComplexList().firstOrNull {
           it.id == complexId
       }?.houses ?: throw NotFoundException("Complex")
    }

    override fun getHouseById(houseId: Long): House? {
        return complexService.getComplexList().flatMap { it.houses }.firstOrNull {
            it.id == houseId
        }
    }

    override fun linkHouse(houseId: Long, templateId: Long) {
        if (!templateRepository.existsById(templateId)) {
            throw NotFoundException("Template")
        }
        displayRepository.updateTemplateIdByHouseId(houseId, templateId)
        val displays = displayRepository.getAllByHouseId(houseId).orEmpty()
        displays.forEach { it -> logService.createLog(1,it.id, "actor ${1} update template to ${templateId} for display ${it.id}") }
    }
}