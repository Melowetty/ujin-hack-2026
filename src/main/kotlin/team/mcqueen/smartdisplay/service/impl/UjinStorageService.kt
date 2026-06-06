package team.mcqueen.smartdisplay.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.domain.storage.StorageInfo
import team.mcqueen.smartdisplay.service.StorageService
import team.mcqueen.smartdisplay.ujin.generated.api.StorageApi
import team.mcqueen.smartdisplay.ujin.generated.model.Room

@Service
class UjinStorageService(
    private val storageApi: StorageApi,
    @Value("\${integration.ujin.access-token}")
    private val ujinToken: String,
) : StorageService {
    override fun getStorageInfo(houseId: Long): StorageInfo {
        try {
            val response = storageApi.getStorageList(
                ujinToken,
                buildings = listOf(houseId.toInt()),
                complexes = null,
            )

            val items = response.body?.data?.items
                ?: throw IllegalStateException("Parking list is empty from ujin parking service")

            val rooms = items.flatMap { it.buildings ?: listOf()}.flatMap { it.rooms ?: listOf() }

            return StorageInfo(
                publicFreeTotal = rooms.count { it.status == Room.Status.FREE && it.assignmentType == Room.AssignmentType.PUBLIC },
                publicOccupiedTotal = rooms.count { it.status == Room.Status.OCCUPIED && it.assignmentType == Room.AssignmentType.PUBLIC },
                privateFreeTotal = rooms.count { it.status == Room.Status.FREE && it.assignmentType == Room.AssignmentType.PRIVATE },
                privateOccupiedTotal = rooms.count { it.status == Room.Status.OCCUPIED && it.assignmentType == Room.AssignmentType.PRIVATE },
                unassignedFreeTotal = rooms.count { it.status == Room.Status.FREE && it.assignmentType == Room.AssignmentType.UNASSIGNED },
                unassignedOccupiedTotal = rooms.count { it.status == Room.Status.OCCUPIED && it.assignmentType == Room.AssignmentType.UNASSIGNED }
            )
        } catch (e: RuntimeException) {
            e.printStackTrace()
            return StorageInfo(
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