import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './modules/home/home.component';
import { LoginComponent } from './modules/login/login.component';
import {FormBuilder, FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RegisterComponent } from './modules/register/register.component';
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {AuthInterceptor} from "./services/auth-interceptor";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    FormBuilder
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
