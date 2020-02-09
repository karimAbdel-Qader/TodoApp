import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {TodoComponent} from './todo/todo.component';


const routes: Routes = [
  // // {path: '', redirectTo: '/Todo', pathMatch: 'full'},
  // // {path: '/Login', component: LoginComponent},
  // {path: '/Todo', component: TodoComponent},
  // {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
