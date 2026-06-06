package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import team.mcqueen.smartdisplay.generated.api.HousesApi
import team.mcqueen.smartdisplay.generated.model.House
import team.mcqueen.smartdisplay.service.HouseService

@RestController
class HousesController(
    private val houseService: HouseService
): HousesApi{

    override fun getHouses(complexId: Long, ): ResponseEntity<List<House>> {
        return ResponseEntity.ok(houseService.getHouses(complexId))

    }
}