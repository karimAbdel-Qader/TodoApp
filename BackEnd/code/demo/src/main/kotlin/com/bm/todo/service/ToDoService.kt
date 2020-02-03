package com.bm.todo.service

import com.bm.todo.model.TODO
import com.bm.todo.repository.ToDoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ToDoService @Autowired constructor(
        val toDoRepository: ToDoRepository) {


    fun getUnfinishedTodos() : List<TODO> = toDoRepository.findByStatus(false)

    fun addTodo(todo: TODO) = toDoRepository.save(todo)

    @Transactional
    fun updateToDoStatus(todoId: Int) {
        var todo = toDoRepository.findById(todoId).get()
        todo.status = true
    }
}