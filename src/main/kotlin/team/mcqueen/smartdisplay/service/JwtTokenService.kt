package team.mcqueen.smartdisplay.service

import java.time.Duration
import java.time.Instant
import java.util.UUID
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.security.oauth2.jwt.JwtException
import org.springframework.stereotype.Service
import team.mcqueen.smartdisplay.exception.InvalidAuthTokenException
import team.mcqueen.smartdisplay.config.JwtProperties
import team.mcqueen.smartdisplay.domain.auth.AuthTokenPair
import team.mcqueen.smartdisplay.domain.auth.DecodedRefreshToken
import team.mcqueen.smartdisplay.domain.auth.entity.UserEntity

@Service
class JwtTokenService(
    private val jwtEncoder: JwtEncoder,
    private val jwtDecoder: JwtDecoder,
    private val jwtProperties: JwtProperties
) {
    fun issueTokens(
        user: UserEntity
    ): AuthTokenPair {
        val now = Instant.now()
        val accessExpiresAt = now.plus(Duration.ofMinutes(jwtProperties.accessTokenLifetimeMinutes))
        val refreshExpiresAt = now.plus(Duration.ofDays(jwtProperties.refreshTokenLifetimeDays))
        val refreshJti = UUID.randomUUID()

        val accessToken = encodeToken(
            user = user,
            issuedAt = now,
            expiresAt = accessExpiresAt,
            tokenType = TOKEN_TYPE_ACCESS,
            jti = null
        )

        val refreshToken = encodeToken(
            user = user,
            issuedAt = now,
            expiresAt = refreshExpiresAt,
            tokenType = TOKEN_TYPE_REFRESH,
            jti = refreshJti
        )

        return AuthTokenPair(
            accessToken = accessToken,
            refreshToken = refreshToken,
            accessTokenExpiresAt = accessExpiresAt,
            refreshTokenExpiresAt = refreshExpiresAt
        )
    }

    fun decodeRefreshToken(
        token: String
    ): DecodedRefreshToken {
        val jwt = decodeToken(token)
        val tokenType = jwt.getClaimAsString(CLAIM_TOKEN_TYPE)

        if (tokenType != TOKEN_TYPE_REFRESH) {
            throw InvalidAuthTokenException("Provided token is not a refresh token")
        }

        val jti = extractJti(jwt)
        val subject = jwt.subject
            ?: throw InvalidAuthTokenException("Refresh token subject is missing")
        val expiresAt = jwt.expiresAt
            ?: throw InvalidAuthTokenException("Refresh token expiration is missing")

        return DecodedRefreshToken(
            jti = jti,
            subject = subject,
            expiresAt = expiresAt
        )
    }

    private fun extractJti(
        jwt: Jwt
    ): UUID {
        val jtiValue = jwt.id ?: jwt.getClaimAsString(CLAIM_JTI)
        ?: throw InvalidAuthTokenException("Refresh token jti is missing")

        return try {
            UUID.fromString(jtiValue)
        } catch (_: IllegalArgumentException) {
            throw InvalidAuthTokenException("Refresh token jti is invalid")
        }
    }

    private fun decodeToken(
        token: String
    ): Jwt {
        return try {
            jwtDecoder.decode(token)
        } catch (_: JwtException) {
            throw InvalidAuthTokenException()
        } catch (_: IllegalArgumentException) {
            throw InvalidAuthTokenException()
        }
    }

    private fun encodeToken(
        user: UserEntity,
        issuedAt: Instant,
        expiresAt: Instant,
        tokenType: String,
        jti: UUID?
    ): String {
        val claimsBuilder = JwtClaimsSet.builder()
            .issuer(jwtProperties.issuer)
            .subject(user.id.toString())
            .issuedAt(issuedAt)
            .expiresAt(expiresAt)
            .claim(CLAIM_TOKEN_TYPE, tokenType)
            .claim(CLAIM_ROLES, listOf(user.role.name))
            .claim(CLAIM_LOGIN, user.login)

        if (jti != null) {
            claimsBuilder.id(jti.toString())
        }

        val claims = claimsBuilder.build()
        val header = JwsHeader
            .with(MacAlgorithm.HS256)
            .type(HEADER_TYPE_JWT)
            .build()

        return jwtEncoder
            .encode(JwtEncoderParameters.from(header, claims))
            .tokenValue
    }

    companion object {
        private const val TOKEN_TYPE_ACCESS = "access"
        private const val TOKEN_TYPE_REFRESH = "refresh"
        private const val CLAIM_TOKEN_TYPE = "token_type"
        private const val CLAIM_JTI = "jti"
        private const val CLAIM_ROLES = "roles"
        private const val CLAIM_LOGIN = "login"
        private const val HEADER_TYPE_JWT = "JWT"
    }
}