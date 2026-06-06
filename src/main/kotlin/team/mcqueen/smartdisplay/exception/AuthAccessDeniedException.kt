package team.mcqueen.smartdisplay.exception

import org.springframework.http.HttpStatus

class AuthAccessDeniedException(
    message: String = "Access denied for this account"
) : CustomException(
    errorCode = HttpStatus.FORBIDDEN,
    errorType = "AUTH_ACCESS_DENIED",
    message = message
)
