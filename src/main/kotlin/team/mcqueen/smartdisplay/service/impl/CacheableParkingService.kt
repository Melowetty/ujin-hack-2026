package team.mcqueen.smartdisplay.service.impl

import java.time.LocalDateTime
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.domain.parking.ParkingInfo
import team.mcqueen.smartdisplay.service.ParkingService

@Service
@Primary
class CacheableParkingService(
    private val directParkingService: UjinParkingService
): ParkingService {

    private var cache: MutableMap<Long, ParkingInfo> = mutableMapOf()
    private var cachedUntilAt: MutableMap<Long, LocalDateTime> = mutableMapOf()

    override fun getParkingInfo(houseId: Long): ParkingInfo {
        val saved = cache[houseId]
        val savedUntilAt = cachedUntilAt[houseId]
        if (saved != null && savedUntilAt?.isAfter(LocalDateTime.now()) == true) {
            return saved
        }

        val actualData = directParkingService.getParkingInfo(houseId)
        cache[houseId] = actualData
        cachedUntilAt[houseId] = LocalDateTime.now().plusMinutes(5)
        return actualData
    }
}