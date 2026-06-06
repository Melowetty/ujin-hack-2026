package team.mcqueen.smartdisplay.domain.auth

import java.time.Instant
import java.util.UUID

data class DecodedRefreshToken(
    val jti: UUID,
    val subject: String,
    val expiresAt: Instant
)
