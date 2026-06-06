package team.mcqueen.smartdisplay.domain.storage

data class StorageInfo(
    val publicFreeTotal: Int,
    val publicOccupiedTotal: Int,
    val privateFreeTotal: Int,
    val privateOccupiedTotal: Int,
    val unassignedFreeTotal: Int,
    val unassignedOccupiedTotal: Int,
)
