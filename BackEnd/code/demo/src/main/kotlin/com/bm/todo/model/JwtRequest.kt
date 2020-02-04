package com.bm.todo.model

import java.io.Serializable

class JwtRequest : Serializable {
    var username: String? = null
    var password: String? = null

    constructor() {}
    constructor(username: String?, password: String?) {
        this.username = username
        this.password = password
    }

    companion object {
        private const val serialVersionID = 1L
    }
}