import {Component, OnInit} from '@angular/core';
import {Todo} from '../ClassTodo';
import {TodoDataService} from '../todo-data.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.scss']
})
export class TodoComponent implements OnInit {

  constructor(private todoService: TodoDataService) {
  }

  todos: Todo[];
  buttonText = 'Done';
  inputText = '';


  ngOnInit() {
    this.getTodos();
  }

  getTodos(): void {
    this.todoService.getTodos().subscribe(todos => this.todos = todos);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) {
      return;
    }
    this.todoService.addTodo({name} as Todo).subscribe(todo => {
      if (!todo.name) {
        this.todos.push(todo);
      }
    });
  }

  clearInput() {
    this.inputText = '';
  }

  delete(todo: Todo): void {
    this.todos = this.todos.filter(t => t !== todo);
    this.todoService.deleteTodo(todo).subscribe();
  }

  update(todo: Todo): void {
    todo.complete = !todo.complete;
    this.todoService.UpdateTodo(todo).subscribe(() => {
      if (todo.complete) {
        this.buttonText = 'Undone';
      } else {
        this.buttonText = 'Done';
      }
    });
  }

}
