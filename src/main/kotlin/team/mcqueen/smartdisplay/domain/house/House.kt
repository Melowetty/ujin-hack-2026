package team.mcqueen.smartdisplay.domain.house

data class House(
    var id: Long,
    var name: String,
    var address: String,
    var floorsNumber: Int,
    var entrancesNumber: Int
)
