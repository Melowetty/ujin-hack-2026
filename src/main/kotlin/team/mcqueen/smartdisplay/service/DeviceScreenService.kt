package team.mcqueen.smartdisplay.service

import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.exception.NotFoundException
import team.mcqueen.smartdisplay.generated.model.DeviceScreen
import team.mcqueen.smartdisplay.generated.model.DeviceScreenRenderedTemplate
import team.mcqueen.smartdisplay.generated.model.Display
import team.mcqueen.smartdisplay.repository.DisplayRepository

@Service
class DeviceScreenService(
    private val deviceRepository: DisplayRepository,
    private val templateService: TemplateService,
    private val houseService: HouseService,
    private val complexService: ComplexService,
    private val emergencyService: EmergencyService,
    private val enrichService: EnrichService,
) {

    fun renderTemplate(token: String): DeviceScreen {
        val device = deviceRepository.getByToken(token)
            ?: throw NotFoundException("Device")

        val template = templateService.getTemplateById(device.templateId ?: 0)
        val house = houseService.getHouseById(device.houseId)
            ?: throw NotFoundException("House")
        val complex = complexService.getComplexByHouseId(house.id)
            ?: throw NotFoundException("Complex")

        val emergencies = emergencyService.getCurrentEmergency(device.id)
        val enriched = enrichService.enrichTemplate(template.body, complex.id,
            Display(
                id = device.id,
                token = device.token,
                name = device.name,
                houseId = device.houseId,
                templateId = device.templateId,
                floor = device.floor,
                entrance = device.entrance
            )
        )

        return DeviceScreen(
            deviceId = device.id,
            complexId = complex.id,
            houseId = device.houseId,
            complexName = device.name,
            houseName = house.name,
            houseAddress = house.address,
            template = template.body,
            renderedTemplate = DeviceScreenRenderedTemplate(
                widgets = enriched,
            ),
            emergencies = emergencies
        )

    }
}