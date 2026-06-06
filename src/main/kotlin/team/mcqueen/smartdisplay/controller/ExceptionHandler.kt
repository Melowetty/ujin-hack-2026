package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import team.mcqueen.smartdisplay.generated.model.Error

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<Error> {
        return ResponseEntity.status(500).body(Error(message = ex.message))
    }
}