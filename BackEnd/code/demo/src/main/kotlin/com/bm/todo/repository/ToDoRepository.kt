package com.bm.todo.repository

import com.bm.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

interface TodoRepository : JpaRepository<Todo,Int> {

    fun findByStatus(status: Boolean) : List<Todo>
}