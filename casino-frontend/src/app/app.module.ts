import { BrowserModule } from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/auth/login/login.component';
import {FormBuilder, FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RegisterComponent } from './pages/auth/register/register.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptor} from "./services/auth/auth-interceptor";
import { AccountComponent } from './pages/account/account.component';
import {registerLocaleData} from "@angular/common";
import localePL from '@angular/common/locales/pl';
import { EditPasswordComponent } from './pages/account/edit-password/edit-password.component';
import { DepositComponent } from './pages/account/deposit/deposit.component';
registerLocaleData(localePL);

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    AccountComponent,
    EditPasswordComponent,
    DepositComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'pl-PL'},
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    FormBuilder
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
