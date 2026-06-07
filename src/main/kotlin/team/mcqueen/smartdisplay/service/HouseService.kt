package team.mcqueen.smartdisplay.service

import team.mcqueen.smartdisplay.generated.model.House
import team.mcqueen.smartdisplay.repository.DisplayRepository

interface HouseService {
    fun getHouses(complexId: Long): List<House>
    fun getHouseById(houseId: Long): House?
    fun linkHouse(houseId: Long, templateId: Long)
}