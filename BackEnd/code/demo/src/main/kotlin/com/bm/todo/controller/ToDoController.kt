package com.bm.todo.controller


import com.bm.todo.dto.TodoDto
import com.bm.todo.model.Todo
import com.bm.todo.service.ToDoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/todos")
class TodoController @Autowired constructor(
        val todoService: ToDoService){


    @GetMapping
    fun getAllTodos() : ResponseEntity<*> {
        var todoList : List<Todo> = todoService.getUnfinishedTodos()
        var toDoListDto = ArrayList<TodoDto>()

        for(toDo: Todo in todoList) {
            var toDoDto = TodoDto(toDo.id,toDo.taskName,toDo.status)
            toDoListDto.add(toDoDto)
        }

        return ResponseEntity.ok(toDoListDto)
    }

    @PostMapping
    fun addTodo(@RequestBody todoDto: TodoDto) : ResponseEntity<*>{
        var todo = todoService.addTodo(todoDto.taskName)

        var createdTodo = TodoDto()
            with(todoDto) {
                id = todo.id
                taskName = todo.taskName
                status = todo.status
            }


        return ResponseEntity.ok(createdTodo)
    }

    @PatchMapping("/{todoId}/status")
    fun finishTask(@PathVariable todoId: Int) : ResponseEntity<*> {
        todoService.toggleStatus(todoId)
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}