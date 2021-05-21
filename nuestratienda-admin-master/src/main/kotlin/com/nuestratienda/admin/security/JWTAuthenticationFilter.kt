package com.nuestratienda.admin.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.nuestratienda.admin.model.Vendedor
import com.nuestratienda.admin.security.SecurityConstants.EXPIRATION_TIME
import com.nuestratienda.admin.security.SecurityConstants.SECRET
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class JWTAuthenticationFilter (
    private val authManager: AuthenticationManager
) :
    UsernamePasswordAuthenticationFilter() {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse): Authentication {
        return try {
            val creds: Vendedor = ObjectMapper()
                .readValue(request.inputStream, Vendedor::class.java)
            authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    creds.correo,
                    creds.password,
                    ArrayList()
                )
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        val token = JWT.create()
            .withSubject((authResult.getPrincipal() as Vendedor).correo)
            .withExpiresAt(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(SECRET.toByteArray()))
        //val body: String = (authResult.getPrincipal() as Vendedor).correo.toString() + " " + token
        val body: String = (authResult.getPrincipal() as Vendedor).correo.toString()
        val id: String = (authResult.getPrincipal() as Vendedor).id.toString()
        //esponse.writer.write(body)
        //response.addHeader("Authorization","Bearer $body")
        response.writer.write("{\"UserID\":\"$id\"," +
                "\"correo\":\"$body\"," +
                "\"token\":\"$token\"}")
        response.writer.flush()
    }

    init {
        setFilterProcessesUrl("/api/vendedor/login")
    }
}

object SecurityConstants {
    const val SECRET = "SECRET_KEY"
    const val EXPIRATION_TIME: Long = 86400000 // 1440 mins  1 dia
    const val TOKEN_PREFIX = "Bearer "
    const val HEADER_STRING = "Authorization"
    const val SIGN_UP_URL = "/api/vendedor/registro"
}