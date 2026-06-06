package team.mcqueen.smartdisplay.service

import org.springframework.stereotype.Repository
import team.mcqueen.smartdisplay.domain.log.LogEntity
import team.mcqueen.smartdisplay.exception.NotFoundException
import team.mcqueen.smartdisplay.generated.model.Log
import team.mcqueen.smartdisplay.repository.LogRepository

@Repository
class LogService (
    private val logRepository: LogRepository
) {
    fun getDisplayLogs(displayId: Long): List<Log> {
        val logs = logRepository.findAllByDisplayId(displayId)
            ?: throw NotFoundException("Logs")
        return logs.map {x -> Log(id = x.id, actor = x.actor, displayId = x.displayId, text = x.text)}
    }

    fun createLog(actor: Long,displayId: Long, text: String) {
        logRepository.save(
            LogEntity (
                actor = actor,
                displayId = displayId,
                text = text
            )
        )
    }
}