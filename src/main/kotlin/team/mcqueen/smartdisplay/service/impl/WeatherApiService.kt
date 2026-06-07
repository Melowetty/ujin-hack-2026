package team.mcqueen.smartdisplay.service.impl

import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.api.WeatherApi
import team.mcqueen.smartdisplay.domain.weather.Weather
import team.mcqueen.smartdisplay.service.WeatherService

@Service
class WeatherApiService(
    private val weatherApi: WeatherApi
) : WeatherService {
    override fun getWeather(city: String): Weather {
        return Weather(
            city = city,
            temperature = 26.5,
            weatherType = "Облачно"
        )
    }
}