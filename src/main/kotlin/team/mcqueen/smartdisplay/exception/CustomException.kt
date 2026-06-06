package team.mcqueen.smartdisplay.exception

import org.springframework.http.HttpStatusCode

open class CustomException(
    val errorCode: HttpStatusCode,
    val errorType: String,
    override val message: String
) : RuntimeException(message)
