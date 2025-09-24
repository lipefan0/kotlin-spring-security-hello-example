package br.com.contis.kotlin_spring_security_hello_example.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Base64
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    @Value("\${app.secret}") private val secret: String
) {
    private val key: SecretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret))

    fun getClaimsFromToken(token: String): Claims {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
            return true
        } catch (ex: Exception) {
            println("Token inválido: ${ex.message}")
        }
        return false
    }
}