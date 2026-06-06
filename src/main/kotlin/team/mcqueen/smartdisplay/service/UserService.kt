package team.mcqueen.smartdisplay.service

import jakarta.transaction.Transactional
import java.util.UUID
import kotlin.jvm.optionals.getOrNull
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.domain.auth.AuthRole
import team.mcqueen.smartdisplay.domain.auth.UserInfo
import team.mcqueen.smartdisplay.domain.auth.entity.UserEntity
import team.mcqueen.smartdisplay.exception.InvalidAuthTokenException
import team.mcqueen.smartdisplay.exception.NotFoundException
import team.mcqueen.smartdisplay.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun getUserInfo(): UserInfo {
        val userId = resolveUserId()
        val user = userRepository.findById(userId).getOrNull()
            ?: throw IllegalStateException("User not found")
        return UserInfo(
            userId,
            name = user.name,
            role = user.role,
        )
    }

    fun getUserById(id: UUID): UserEntity? {
        val user = userRepository.findById(id).getOrNull()
        return user
    }

    fun getUserByLogin(login: String): UserEntity? {
        return userRepository.findByLogin(login)
    }

    fun createUserByLoginAndPassword(name: String, login: String, password: String): UserEntity {
        return userRepository.save(
            UserEntity(
                name = name,
                login = login,
                password = password,
                role = AuthRole.USER,
            )
        )
    }

    @Transactional
    fun setUserRole(userId: UUID, role: AuthRole) {
        val user = userRepository.findById(userId).getOrNull()
            ?: throw NotFoundException("User")

        user.role = role

        userRepository.save(user)
    }

    fun resolveSubjectOrNull(): String? =
        extractJwt(SecurityContextHolder.getContext().authentication)
            ?.subject
            ?.takeIf { it.isNotBlank() }

    fun resolveUserIdOrNull(): UUID? =
        try {
            resolveSubjectOrNull()?.let(UUID::fromString)
        } catch (_: IllegalArgumentException) {
            null
        }

    fun resolveUserId(): UUID =
        resolveUserIdOrNull()
            ?: throw InvalidAuthTokenException("Authentication token subject is invalid or missing")

    private fun extractJwt(authentication: Authentication?): Jwt? =
        when (authentication) {
            is JwtAuthenticationToken -> authentication.token
            else -> authentication?.principal as? Jwt
        }
}