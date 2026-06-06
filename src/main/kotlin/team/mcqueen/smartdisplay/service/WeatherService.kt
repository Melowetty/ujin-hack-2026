package team.mcqueen.smartdisplay.service

import team.mcqueen.smartdisplay.domain.weather.Weather

interface WeatherService {
    fun getWeather(): Weather
}