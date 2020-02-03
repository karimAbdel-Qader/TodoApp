package com.bm.todo.controller

import com.bm.todo.dto.ToDoDto
import com.bm.todo.model.TODO
import com.bm.todo.service.ToDoService
import org.modelmapper.ModelMapper
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
        var todoList : List<TODO> = toDoService.getUnfinishedTodos()
        var toDoListDto = ArrayList<ToDoDto>()

        for(toDo: TODO in todoList) {
            var toDoDto = ToDoDto(toDo.id,toDo.task,toDo.status)
            toDoListDto.add(toDoDto)
        }

        return ResponseEntity.ok(toDoListDto)
    }

    @PostMapping
    fun addTodo(@RequestBody todoDto: ToDoDto) : ResponseEntity<*>{
        var todo = com.bm.todo.model.TODO(0,todoDto.task,todoDto.status)
        toDoService.addTodo(todo)

        todoDto.id = todo.id
        return ResponseEntity.ok(todoDto)
    }

    @PatchMapping("/{todoId}/status")
    fun finishTask(@PathVariable todoId: Int) : ResponseEntity<*> {
        toDoService.updateToDoStatus(todoId)

        return ResponseEntity<Void>(HttpStatus.OK)
    }
}