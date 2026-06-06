package team.mcqueen.smartdisplay.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.domain.parking.ParkingInfo
import team.mcqueen.smartdisplay.service.ParkingService
import team.mcqueen.smartdisplay.ujin.generated.api.ParkingApi
import team.mcqueen.smartdisplay.ujin.generated.model.Spot

@Service
class UjinParkingService(
    private val parkingApi: ParkingApi,
    @Value("\${integration.ujin.access-token}")
    private val ujinToken: String,
): ParkingService {

    override fun getParkingInfo(houseId: Long): ParkingInfo {
        try {
            val response = parkingApi.getParkingList(
                ujinToken,
                buildings = listOf(houseId.toInt()),
                complexes = null,
            )

            val items = response.body?.data?.items
                ?: throw IllegalStateException("Parking list is empty from ujin parking service")

            val spots = items.flatMap { it.buildings ?: listOf()}.flatMap { it.zones ?: listOf() }
                .flatMap { it.spots ?: listOf() }

            return ParkingInfo(
                publicFreeTotal = spots.count { it.status == Spot.Status.FREE && it.assignmentType == Spot.AssignmentType.PUBLIC },
                publicOccupiedTotal = spots.count { it.status == Spot.Status.OCCUPIED && it.assignmentType == Spot.AssignmentType.PUBLIC },
                privateFreeTotal = spots.count { it.status == Spot.Status.FREE && it.assignmentType == Spot.AssignmentType.PRIVATE },
                privateOccupiedTotal = spots.count { it.status == Spot.Status.OCCUPIED && it.assignmentType == Spot.AssignmentType.PRIVATE },
                unassignedFreeTotal = spots.count { it.status == Spot.Status.FREE && it.assignmentType == Spot.AssignmentType.UNASSIGNED },
                unassignedOccupiedTotal = spots.count { it.status == Spot.Status.OCCUPIED && it.assignmentType == Spot.AssignmentType.UNASSIGNED }
            )
        } catch (e: RuntimeException) {
            e.printStackTrace()
            return ParkingInfo(
                publicFreeTotal = 3,
                publicOccupiedTotal = 6,
                privateFreeTotal = 4,
                privateOccupiedTotal = 8,
                unassignedFreeTotal = 5,
                unassignedOccupiedTotal = 10,
            )
        }
    }
}