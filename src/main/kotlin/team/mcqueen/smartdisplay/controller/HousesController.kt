package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import team.mcqueen.smartdisplay.generated.api.HousesApi
import team.mcqueen.smartdisplay.generated.model.House

@RestController
class HousesController: HousesApi{

    override fun getHouses(): ResponseEntity<List<House>> {
        return ResponseEntity.ok(listOf(
            House(
                id = 1,
                name = "Дом на Ленине",
                floorsNumber = 3,
                address = "ул. Ленина 67",
                entrancesNumber = 2,
                city = "Пермь"
            ),
            House(
                id = 2,
                name = "Дом на Парковой",
                address = "ул. Парковая 41",
                floorsNumber = 10,
                entrancesNumber = 5,
                city = "Москва"
            )
            )
        )
    }
}