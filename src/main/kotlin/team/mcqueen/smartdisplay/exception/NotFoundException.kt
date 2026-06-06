package team.mcqueen.smartdisplay.exception

import org.springframework.http.HttpStatus

class NotFoundException(
    entity: String,
): CustomException(
    HttpStatus.NOT_FOUND,
    errorType = "ENTITY_NOT_FOUND",
    message = "$entity is not found"
)