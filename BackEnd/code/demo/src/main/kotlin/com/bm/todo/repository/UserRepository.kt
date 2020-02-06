package com.bm.todo.repository

import com.bm.todo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User,Int> {
    fun findByStatus(status: Boolean) : List<User>
}