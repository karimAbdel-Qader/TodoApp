package com.bm.todo.service

import com.bm.todo.model.Todo
import com.bm.todo.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 *
 * This class has handling all the logic for the Todos.
 *
 * @param todoRepository repository for Todos.
 * @constructor Creates a ToDoService with repository.
 */
@Service
class ToDoService @Autowired constructor(
        val todoRepository: TodoRepository) {


    /**
     * Fetch all unfinished ToDos
     */
    fun getUnfinishedTodos() : List<Todo> = todoRepository.findByStatus(false)

    /**
     * Creates new Tod
     *
     * @param todo
     */
    fun addTodo(taskName: String) : Todo {
        var todo = Todo(taskName)
        return todoRepository.save(todo)
    }

    /**
     * Update specific Tod by it's id
     *
     * @param todoId
     */
    @Transactional
    fun toggleStatus(todoId: Int) {
        var todo = todoRepository.findById(todoId).get()
        todo.status = true
    }
}