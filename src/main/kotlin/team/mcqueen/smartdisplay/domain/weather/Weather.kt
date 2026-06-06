package team.mcqueen.smartdisplay.domain.weather

data class Weather(
    var city: String,
    var temperature: Int,
    var weatherType: String
)
