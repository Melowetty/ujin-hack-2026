package team.mcqueen.smartdisplay.service

import team.mcqueen.smartdisplay.generated.model.House

interface HouseService {
    fun getHouses(complexId: Long): List<House>
    fun getHouseById(houseId: Long): House?
}