package team.mcqueen.smartdisplay.domain.parking

data class ParkingInfo(
    val publicFreeTotal: Int,
    val publicOccupiedTotal: Int,
    val privateFreeTotal: Int,
    val privateOccupiedTotal: Int,
    val unassignedFreeTotal: Int,
    val unassignedOccupiedTotal: Int,
)
