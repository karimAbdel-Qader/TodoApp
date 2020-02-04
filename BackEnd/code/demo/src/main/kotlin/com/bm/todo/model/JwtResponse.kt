package com.bm.todo.model

import java.io.Serializable

class JwtResponse(val token: String) : Serializable {

    companion object {
        private const val serialVersionUID = -2384L
    }

}