package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import team.mcqueen.smartdisplay.generated.api.WidgetsApi
import team.mcqueen.smartdisplay.generated.model.FieldType
import team.mcqueen.smartdisplay.generated.model.Widget
import team.mcqueen.smartdisplay.generated.model.WidgetField
import team.mcqueen.smartdisplay.generated.model.WidgetType

@RestController
class WidgetsController : WidgetsApi{
    override fun getWidgets(): ResponseEntity<List<Widget>> {
        return ResponseEntity.ok(listOf(
            Widget(
                name="Погода",
                type = WidgetType.WEATHER,
                fields = listOf(
                    WidgetField(
                        name = "Температура",
                        type = FieldType.INTEGER,
                        optional = false,
                    ),
                    WidgetField(
                        name = "Облачность",
                        type = FieldType.STRING,
                        optional = false,
                    ),
                    WidgetField(
                        name = "Давление",
                        type = FieldType.INTEGER,
                        optional = true,
                    )
                )
            ),
            Widget(
                name="Парковка",
                type = WidgetType.PARKING,
                fields = listOf(
                    WidgetField(
                        name = "Свободные места",
                        type = FieldType.INTEGER,
                        optional = false,
                    ),
                    WidgetField(
                        name = "Всего мест",
                        type = FieldType.INTEGER,
                        optional = false,
                    )
                )
            )

        ))
    }
}