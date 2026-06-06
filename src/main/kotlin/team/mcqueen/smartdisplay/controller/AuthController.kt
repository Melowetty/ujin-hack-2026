package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import team.mcqueen.smartdisplay.generated.api.AuthApi
import team.mcqueen.smartdisplay.generated.model.LoginRequest
import team.mcqueen.smartdisplay.generated.model.RefreshRequest
import team.mcqueen.smartdisplay.generated.model.RegisterRequest
import team.mcqueen.smartdisplay.generated.model.TokenResponse
import java.time.OffsetDateTime

class AuthController : AuthApi {
    override fun login(loginRequest: LoginRequest): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(TokenResponse(
            accessToken = "EtoAccessTokenPrivetMisha",
            refreshToken = "LolMishaPRIVETTestishApi?",
            tokenType = "Bearer",
            accessTokenExpiresAt = OffsetDateTime.now(),
            refreshTokenExpiresAt = OffsetDateTime.now()
        ))
    }

    override fun refreshToken(refreshRequest: RefreshRequest): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(TokenResponse(
            accessToken = "EtoAccessTokenPrivetMisha",
            refreshToken = "LolMishaPRIVETTestishApi?",
            tokenType = "Bearer",
            accessTokenExpiresAt = OffsetDateTime.now(),
            refreshTokenExpiresAt = OffsetDateTime.now()
        ))
    }

    override fun register(registerRequest: RegisterRequest): ResponseEntity<Unit> {
        return ResponseEntity.ok().build()
    }
}