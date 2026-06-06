package team.mcqueen.smartdisplay.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "security.jwt")
data class JwtProperties(
    var issuer: String = "",
    var secret: String = "",
    var accessTokenLifetimeMinutes: Long = 15,
    var refreshTokenLifetimeDays: Long = 30
)
