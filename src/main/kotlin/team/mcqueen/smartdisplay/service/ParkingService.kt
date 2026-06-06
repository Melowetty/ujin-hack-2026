package team.mcqueen.smartdisplay.service

import team.mcqueen.smartdisplay.domain.parking.ParkingInfo

interface ParkingService {
    fun getParkingInfo(houseId: Long): ParkingInfo
}