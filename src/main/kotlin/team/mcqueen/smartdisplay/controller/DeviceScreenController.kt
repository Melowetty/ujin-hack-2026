package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import team.mcqueen.smartdisplay.generated.api.DisplayScreenApi
import team.mcqueen.smartdisplay.generated.model.DeviceScreen
import team.mcqueen.smartdisplay.service.DeviceScreenService

@RestController
class DeviceScreenController(
    private val deviceScreenService: DeviceScreenService,
): DisplayScreenApi {

    override fun getDeviceScreen(token: String): ResponseEntity<DeviceScreen> {
        return ResponseEntity.ok(deviceScreenService.renderTemplate(token))
    }
}