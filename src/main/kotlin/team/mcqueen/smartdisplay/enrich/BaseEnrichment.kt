package team.mcqueen.smartdisplay.enrich

import team.mcqueen.smartdisplay.domain.enrich.Enriched
import team.mcqueen.smartdisplay.generated.model.Display
import team.mcqueen.smartdisplay.generated.model.WidgetType

interface BaseEnrichment {
    fun enrich(widget: WidgetType, complexId: Long, device: Display): Enriched
    fun getWidgetType(): WidgetType
}