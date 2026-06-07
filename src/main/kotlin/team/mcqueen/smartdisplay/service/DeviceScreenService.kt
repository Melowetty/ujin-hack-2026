package team.mcqueen.smartdisplay.service

import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.exception.NotFoundException
import team.mcqueen.smartdisplay.generated.model.DeviceScreen
import team.mcqueen.smartdisplay.generated.model.DeviceScreenRenderedTemplate
import team.mcqueen.smartdisplay.generated.model.RenderedWidget
import team.mcqueen.smartdisplay.generated.model.WidgetType
import team.mcqueen.smartdisplay.repository.DisplayRepository

@Service
class DeviceScreenService(
    private val deviceRepository: DisplayRepository,
    private val templateService: TemplateService,
    private val houseService: HouseService,
    private val complexService: ComplexService,
    private val emergencyService: EmergencyService,
) {

    fun renderTemplate(token: String): DeviceScreen {
        val device = deviceRepository.getByToken(token)
            ?: throw NotFoundException("Device")

        val template = templateService.getTemplateById(device.id)
        val house = houseService.getHouseById(device.houseId)
            ?: throw NotFoundException("House")
        val complex = complexService.getComplexByHouseId(house.id)
            ?: throw NotFoundException("Complex")

        val emergencies = emergencyService.getCurrentEmergency(device.id)

        return DeviceScreen(
            deviceId = device.id,
            complexId = complex.id,
            houseId = device.houseId,
            complexName = device.name,
            houseName = house.name,
            houseAddress = house.address,
            template = template.body,
            renderedTemplate = DeviceScreenRenderedTemplate(
                widgets = listOf(
                    RenderedWidget(
                        id = "weather-1",
                        type = WidgetType.WEATHER,
                        body = mapOf(
                            "kok" to "kok"
                        )
                    )
                )
            ),
            emergencies = emergencies
        )

    }
}