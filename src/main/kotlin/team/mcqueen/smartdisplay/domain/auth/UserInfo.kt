package team.mcqueen.smartdisplay.domain.auth

import java.util.UUID

data class UserInfo(
    val id: UUID,
    val name: String,
    val role: AuthRole,
)
