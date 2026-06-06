package team.mcqueen.smartdisplay.domain.auth

import java.time.Instant

data class AuthTokenPair(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiresAt: Instant,
    val refreshTokenExpiresAt: Instant
)
