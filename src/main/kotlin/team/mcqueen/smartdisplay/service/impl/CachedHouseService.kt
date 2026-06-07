package team.mcqueen.smartdisplay.service.impl

import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.exception.NotFoundException
import team.mcqueen.smartdisplay.generated.model.House
import team.mcqueen.smartdisplay.service.ComplexService
import team.mcqueen.smartdisplay.service.HouseService

@Service
class CachedHouseService(
    private val complexService: ComplexService,
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
}