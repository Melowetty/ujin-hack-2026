package team.mcqueen.smartdisplay.service

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.enrich.BaseEnrichment
import team.mcqueen.smartdisplay.generated.model.Display
import team.mcqueen.smartdisplay.generated.model.RenderedWidget
import team.mcqueen.smartdisplay.generated.model.WidgetType

@Service
class EnrichService(
    val enrichment: List<BaseEnrichment>
) {

    fun enrichTemplate(template: Any, complexId: Long, device: Display): List<RenderedWidget> {
        val widgets = getWidgets(template as JsonNode)

        return widgets.mapNotNull { widget ->
            val enriched =
                getEnrichProcessor(widget.second)?.enrich(widget.second, complexId, device) ?: return@mapNotNull null

            RenderedWidget(
                id = widget.first,
                type = widget.second,
                body = enriched,
            )
        }
    }

    fun getEnrichProcessor(widgetType: WidgetType): BaseEnrichment? {
        return enrichment.firstOrNull { it.getWidgetType() == widgetType }
    }

    fun getWidgets(template: JsonNode): List<Pair<String, WidgetType>> {
        val widgetsLayout = template["layout"]
        val size = widgetsLayout.size()

        val result = mutableListOf<Pair<String, WidgetType>>()

        for (i in 0 until size) {
            val widget = widgetsLayout[i]
            val widgetId = widget.get("i").asText()
            val widgetTypeRaw = widget.get("type").asText()
            val widgetType = WidgetType.valueOf(widgetTypeRaw)

            result.add(Pair(widgetId, widgetType))
        }

        return result
    }
}