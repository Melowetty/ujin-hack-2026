package team.mcqueen.smartdisplay.config

import com.nimbusds.jose.jwk.source.ImmutableSecret
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import java.nio.charset.StandardCharsets
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class JwtConfiguration {
    @Bean
    fun jwtSecretKey(
        jwtProperties: JwtProperties
    ): SecretKey {
        val secretBytes = jwtProperties.secret.toByteArray(StandardCharsets.UTF_8)
        require(secretBytes.size >= MIN_SECRET_BYTES) {
            "security.jwt.secret must contain at least $MIN_SECRET_BYTES bytes"
        }

        return SecretKeySpec(secretBytes, HMAC_SHA256)
    }

    @Bean
    fun jwtDecoder(
        jwtSecretKey: SecretKey
    ): JwtDecoder {
        return NimbusJwtDecoder
            .withSecretKey(jwtSecretKey)
            .macAlgorithm(MacAlgorithm.HS256)
            .build()
    }

    @Bean
    fun jwtEncoder(
        jwtSecretKey: SecretKey
    ): JwtEncoder {
        return NimbusJwtEncoder(ImmutableSecret(jwtSecretKey))
    }

    @Bean
    fun jwtAuthenticationConverter(): Converter<Jwt, out AbstractAuthenticationToken> {
        val converter = JwtAuthenticationConverter()

        converter.setJwtGrantedAuthoritiesConverter { jwt ->
            jwt.getClaimAsStringList(CLAIM_ROLES)
                ?.map(::toRoleAuthority).orEmpty()
        }

        return converter
    }

    private fun toRoleAuthority(role: String): GrantedAuthority {
        return SimpleGrantedAuthority("$ROLE_PREFIX$role")
    }

    companion object {
        private const val HMAC_SHA256 = "HmacSHA256"
        private const val MIN_SECRET_BYTES = 32
        private const val CLAIM_ROLES = "roles"
        private const val ROLE_PREFIX = "ROLE_"
    }
}
