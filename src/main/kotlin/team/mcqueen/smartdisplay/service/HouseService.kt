package team.mcqueen.smartdisplay.service

import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.generated.model.House

@Service
class HouseService {
    fun getHouses(complexId: Long): List<House> {
       return listOf(
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
               name = "Дом на Ленине",
               floorsNumber = 10,
               address = "ул. Ленина 66",
               entrancesNumber = 2,
               city = "Пермь"
           )
       )
    }
}