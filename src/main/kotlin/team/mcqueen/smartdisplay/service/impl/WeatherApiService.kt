package team.mcqueen.smartdisplay.service.impl

import team.mcqueen.smartdisplay.api.WeatherApi
import team.mcqueen.smartdisplay.domain.weather.Weather
import team.mcqueen.smartdisplay.service.WeatherService

class WeatherApiService(
    private val weatherApi: WeatherApi
) : WeatherService {
    override fun getWeather(city: String): Weather {
        return weatherApi.getWeatherByCity(city)
    }
}