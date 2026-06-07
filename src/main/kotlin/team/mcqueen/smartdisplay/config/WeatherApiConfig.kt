package team.mcqueen.smartdisplay.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class WeatherApiConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}