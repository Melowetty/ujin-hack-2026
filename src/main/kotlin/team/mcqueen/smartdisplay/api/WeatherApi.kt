package team.mcqueen.smartdisplay.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import team.mcqueen.smartdisplay.domain.weather.Weather
import team.mcqueen.smartdisplay.exception.NotFoundException

@Component
class WeatherApi(
    private val restTemplate: RestTemplate,
    @Value("\${integration.weather-api.access-token}")
    private var weatherToken: String,
    @Value("\${integration.geo-api.access-token}")
    private var geoToken: String
) {

    fun getWeatherByCity(city: String): Weather {
        val (lat, lon) = getCoordinateByCity(city)

        val weatherUrl = "https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=$weatherToken"
        val weatherAnswer = restTemplate.getForObject(weatherUrl,  Map::class.java) as Map<String, Any>

        val body = weatherAnswer["main"] as Map<String, Any>
        val temperature = body["temp"] as Double
        val weatherArray = weatherAnswer["weather"] as List<Map<String, Any>>
        val weatherType = weatherArray.first()["main"] as String

        return Weather(
            city = city,
            temperature = temperature,
            weatherType = weatherType
        )
    }

    fun getCoordinateByCity(city: String): Pair<Double, Double>{
        val geocoderUrl = "https://geocode-maps.yandex.ru/v1/?apikey=$geoToken&geocode=$city&format=json"
        val geo = restTemplate.getForObject(geocoderUrl,  Map::class.java) as Map<String, Any>

        val geoObjectCollection = (geo["response"] as Map<String, Any>)["GeoObjectCollection"] as Map<String, Any>
        val featureMember = geoObjectCollection["featureMember"] as List<Map<String, Any>>

        if (featureMember.isEmpty()) {
            throw NotFoundException("City '$city' not found")
        }

        val geoObject = featureMember.first()["GeoObject"] as Map<String, Any>
        val point = geoObject["Point"] as Map<String, Any>
        val pos = point["pos"] as String  // "longitude latitude"
        val (lon, lat) = pos.split(" ").map { it.toDouble() }

        return Pair(lat, lon)
    }
}