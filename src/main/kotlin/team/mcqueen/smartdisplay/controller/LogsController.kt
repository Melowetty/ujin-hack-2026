package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import team.mcqueen.smartdisplay.generated.api.LogsApi
import team.mcqueen.smartdisplay.generated.model.Log
import team.mcqueen.smartdisplay.service.LogService

@RestController
class LogsController(
    private val logsService: LogService,
): LogsApi {
    override fun getDisplayLogs(complexId: Long, id: Long, displayId: Long): ResponseEntity<List<Log>> {
        return ResponseEntity.ok(logsService.getDisplayLogs(displayId))
    }
}