package team.mcqueen.smartdisplay.service

import java.util.UUID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.mcqueen.smartdisplay.domain.auth.AuthTokenPair
import team.mcqueen.smartdisplay.exception.AuthAccessDeniedException
import team.mcqueen.smartdisplay.exception.InvalidAuthTokenException

@Service
class AuthService(
    private val jwtTokenService: JwtTokenService,
    private val userService: UserService,
) {

    fun registerUser(name: String, login: String, password: String) {
        userService.createUserByLoginAndPassword(
            name = name,
            login = login,
            password = password,
        )
    }

    fun loginByPassword(
        login: String,
        password: String
    ): AuthTokenPair {
        val user = userService.getUserByLoginAndPassword(login, password)
            ?: throw AuthAccessDeniedException("User not found or incorrect password")
        return jwtTokenService.issueTokens(user)
    }

    @Transactional
    fun refresh(
        refreshToken: String
    ): AuthTokenPair {
        val decodedToken = jwtTokenService.decodeRefreshToken(refreshToken)

        val userId = try {
            UUID.fromString(decodedToken.subject)
        } catch (_: IllegalArgumentException) {
            throw InvalidAuthTokenException("Refresh token subject is invalid")
        }

        val user = userService.getUserById(userId)
            ?: throw InvalidAuthTokenException("Token subject user does not exist")

        return jwtTokenService.issueTokens(user)
    }
}