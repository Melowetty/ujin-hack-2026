package team.mcqueen.smartdisplay.enrich

import org.springframework.stereotype.Component
import team.mcqueen.smartdisplay.domain.enrich.Enriched
import team.mcqueen.smartdisplay.domain.enrich.EnrichedWrapper
import team.mcqueen.smartdisplay.generated.model.Display
import team.mcqueen.smartdisplay.generated.model.WidgetType
import team.mcqueen.smartdisplay.service.ParkingService

@Component
class ParkingEnrichment(
    private val parkingService: ParkingService,
): BaseEnrichment {
    override fun enrich(
        complexId: Long,
        device: Display
    ): Enriched {
        return EnrichedWrapper(parkingService.getParkingInfo(device.houseId))
    }

    override fun getWidgetType(): WidgetType {
       return WidgetType.PARKING
    }
}