package team.mcqueen.smartdisplay.exception

import org.springframework.http.HttpStatus

class InvalidAuthTokenException(
    message: String = "Invalid or expired authentication token"
) : CustomException(
    errorCode = HttpStatus.UNAUTHORIZED,
    errorType = "INVALID_AUTH_TOKEN",
    message = message
)
