package team.mcqueen.smartdisplay.enrich

import org.springframework.stereotype.Component
import team.mcqueen.smartdisplay.domain.enrich.Enriched
import team.mcqueen.smartdisplay.domain.enrich.EnrichedWrapper
import team.mcqueen.smartdisplay.generated.model.Display
import team.mcqueen.smartdisplay.generated.model.WidgetType
import team.mcqueen.smartdisplay.service.WeatherService

@Component
class WeatherEnrichment(
    private val weatherService: WeatherService,
): BaseEnrichment {
    override fun enrich(
        complexId: Long,
        device: Display
    ): Enriched {
        return EnrichedWrapper(weatherService.getWeather("Пермь"))
    }

    override fun getWidgetType(): WidgetType {
        return WidgetType.WEATHER
    }
}