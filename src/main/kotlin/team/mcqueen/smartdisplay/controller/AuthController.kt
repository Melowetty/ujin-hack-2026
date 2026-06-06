package team.mcqueen.smartdisplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import team.mcqueen.smartdisplay.generated.api.AuthApi
import team.mcqueen.smartdisplay.generated.model.LoginRequest
import team.mcqueen.smartdisplay.generated.model.RefreshRequest
import team.mcqueen.smartdisplay.generated.model.RegisterRequest
import team.mcqueen.smartdisplay.generated.model.TokenResponse
import java.time.ZoneOffset
import team.mcqueen.smartdisplay.service.AuthService

@RestController
class AuthController(
    private val authService: AuthService,
) : AuthApi {
    override fun login(loginRequest: LoginRequest): ResponseEntity<TokenResponse> {
        val token = authService.loginByPassword(loginRequest.login, loginRequest.password)
        return ResponseEntity.ok(
            TokenResponse(
                accessToken = token.accessToken,
                refreshToken = token.refreshToken,
                accessTokenExpiresAt = token.accessTokenExpiresAt.atOffset(ZoneOffset.UTC),
                refreshTokenExpiresAt = token.refreshTokenExpiresAt.atOffset(ZoneOffset.UTC),
            )
        )
    }

    override fun refreshToken(refreshRequest: RefreshRequest): ResponseEntity<TokenResponse> {
        val token = authService.refresh(refreshRequest.refreshToken)
        return ResponseEntity.ok(
            TokenResponse(
                accessToken = token.accessToken,
                refreshToken = token.refreshToken,
                accessTokenExpiresAt = token.accessTokenExpiresAt.atOffset(ZoneOffset.UTC),
                refreshTokenExpiresAt = token.refreshTokenExpiresAt.atOffset(ZoneOffset.UTC),
            )
        )
    }

    override fun register(registerRequest: RegisterRequest): ResponseEntity<Unit> {
        authService.registerUser(registerRequest.name, registerRequest.login, registerRequest.password)
        return ResponseEntity.ok().build()
    }
}