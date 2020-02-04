package com.bm.todo.controller

import com.bm.todo.config.JwtTokenUtil
import com.bm.todo.model.JwtRequest
import com.bm.todo.model.JwtResponse
import com.bm.todo.service.JwtUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
class JwtAuthenticationController {
    @Autowired
    private val authenticationManager: AuthenticationManager? = null
    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null
    @Autowired
    private val userDetailsService: JwtUserDetailsService? = null

    @RequestMapping(value = ["/v1/auth"], method = [RequestMethod.POST])
    @Throws(Exception::class)
    fun createAuthenticationToken(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<*> {
        authenticate(authenticationRequest.username.toString(), authenticationRequest.password.toString())
        val userDetails: UserDetails = userDetailsService!!.loadUserByUsername(authenticationRequest.username.toString())
        val token: String = jwtTokenUtil!!.generateToken(userDetails)
        return ResponseEntity.ok<Any>(JwtResponse(token))
    }

    @Throws(Exception::class)
    private fun authenticate(username: String, password: String) {
        try {
            authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }
}