package com.bm.todo.service

import com.bm.todo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUserDetailsService : UserDetailsService {

    @Autowired
    var userDao: UserRepository? = null;

    @Autowired
    var bcryptEncoder: PasswordEncoder? = null;

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {

        

        return if ("nargacuga" == username) {
            User("nargacuga", "$2a$10\$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    ArrayList())
        } else {
            throw UsernameNotFoundException("User not found with username: $username")
        }
    }
}