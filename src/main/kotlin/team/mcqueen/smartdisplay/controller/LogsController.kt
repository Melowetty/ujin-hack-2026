package team.mcqueen.smartdisplay.controller

import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import team.mcqueen.smartdisplay.generated.api.LogsApi
import team.mcqueen.smartdisplay.generated.model.Log

class LogsController: LogsApi {
    override fun getDisplayLogs(id: Long, displayId: Long): ResponseEntity<List<Log>> {
        return ResponseEntity.ok(listOf(
            Log(
                id = 1,
                actor = 1,
                displayId = 1,
                text = "Установлен шаблон 1"
            ),
            Log(
                id = 2,
                actor = 1,
                displayId = 1,
                text = "Установлен шаблон 2"
            ),
            Log(
                id = 3,
                actor = 1,
                displayId = 2,
                text = "Установлен шаблон 2"
            )
        ))
    }
}