package com.bm.todo.repository

import com.bm.todo.model.TODO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ToDoRepository : JpaRepository<TODO,Int> {

    fun findByStatus(status: Boolean) : List<TODO>
}