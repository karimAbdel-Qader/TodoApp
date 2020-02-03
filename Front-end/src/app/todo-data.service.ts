import {Injectable} from '@angular/core';
import {Todo} from './ClassTodo';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MessageService} from './message.service';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {
  private apiUrl = 'api/v1/Todos';
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private message: MessageService, private http: HttpClient) {
  }

  private log(message: string) {
    this.message.add(`HeroService: ${message}`);
  }

// GET ALL TODOO
  getTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(this.apiUrl)
      .pipe(
        tap(_ => this.log('fetched todos')),
        catchError(this.handleError('getTodos', []))
      );
  }

  // ADD A TODOO
  addTodo(todo: Todo): Observable<Todo> {

    return this.http.post<Todo>(this.apiUrl, todo, this.httpOptions).pipe(
      tap(_ => this.log(`added todo w/ id=${todo.id}`)),
      catchError(this.handleError<Todo>('addTodo'))
    );
  }

  // DELETE A TODOO
  deleteTodo(todo: Todo | number): Observable<Todo> {
    const id = typeof todo === 'number' ? todo : todo.id;
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<Todo>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted todo id=${id}`)),
      catchError(this.handleError<Todo>('deleteTodo'))
    );
  }

  // UPDATE A TODOO

  UpdateTodo(todo: Todo): Observable<any> {
    return this.http.put(this.apiUrl, this.httpOptions).pipe(
      tap(_ => this.log(`updated todo id=${todo.id}`)),
      catchError(this.handleError<Todo>('updateTodo'))
    );

  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
