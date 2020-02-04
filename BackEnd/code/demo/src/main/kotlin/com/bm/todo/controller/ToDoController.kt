package com.bm.todo.controller

import com.bm.todo.dto.ToDoDto
import com.bm.todo.model.Todo
import com.bm.todo.service.ToDoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/todos")
class ToDoController @Autowired constructor(
        val toDoService: ToDoService){


    @GetMapping
    fun getAllTodos() : ResponseEntity<*> {
        var todoList : List<Todo> = toDoService.getUnfinishedTodos()
        var toDoListDto = ArrayList<ToDoDto>()

        for(toDo: Todo in todoList) {
            var toDoDto = ToDoDto(toDo.id,toDo.taskName,toDo.status)
            toDoListDto.add(toDoDto)
        }

        return ResponseEntity.ok(toDoListDto)
    }

    @PostMapping
    fun addTodo(@RequestBody todoDto: ToDoDto) : ResponseEntity<*>{
        var todo = toDoService.addTodo(todoDto.taskName)

        var createdTodo = ToDoDto()
            with(todoDto) {
                id = todo.id
                taskName = todo.taskName
                status = todo.status
            }

        return ResponseEntity.ok(createdTodo)
    }

    @PatchMapping("/{todoId}/status")
    fun finishTask(@PathVariable todoId: Int) : ResponseEntity<*> {
        toDoService.toggleStatus(todoId)
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}