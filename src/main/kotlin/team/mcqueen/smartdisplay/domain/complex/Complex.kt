package team.mcqueen.smartdisplay.domain.complex

import team.mcqueen.smartdisplay.domain.house.House

data class Complex(
    var id: Long,
    var name: String,
    var houses: List<House>
)
