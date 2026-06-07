package team.mcqueen.smartdisplay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import team.mcqueen.smartdisplay.service.impl.WeatherApiService

@SpringBootApplication
class SmartDisplaysApplication

fun main(args: Array<String>) {
    runApplication<SmartDisplaysApplication>(*args)
}
