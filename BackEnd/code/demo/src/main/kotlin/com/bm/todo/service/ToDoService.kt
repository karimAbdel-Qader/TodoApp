package com.bm.todo.service

import com.bm.todo.model.Todo
import com.bm.todo.repository.ToDoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 *
 * This class has handling all the logic for the Todos.
 *
 * @param toDoRepository repository for Todos.
 * @constructor Creates a ToDoService with repository.
 */
@Service
class ToDoService @Autowired constructor(
        val toDoRepository: ToDoRepository) {


    /**
     * Fetch all unfinished ToDos
     */
    fun getUnfinishedTodos() : List<Todo> = toDoRepository.findByStatus(false)

    /**
     * Creates new Tod
     *
     * @param todo
     */
    fun addTodo(todo: Todo) = toDoRepository.save(todo)

    /**
     * Update specific Tod by it's id
     *
     * @param todoId
     */
    @Transactional
    fun updateToDoStatus(todoId: Int) {
        var todo = toDoRepository.findById(todoId).get()
        todo.status = true
    }
}