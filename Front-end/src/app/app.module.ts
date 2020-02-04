import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {LoginComponent} from './login/login.component';
import {TodoComponent} from './todo/todo.component';
import {MessagesComponent} from './messages/messages.component';
import {FormsModule} from '@angular/forms';
import {AuthenticationInterceptorService} from './authentication-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TodoComponent,
    MessagesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
