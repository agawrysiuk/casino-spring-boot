import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './pages/home/home.component';
import {LoginComponent} from './pages/auth/login/login.component';
import {FormBuilder, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RegisterComponent} from './pages/auth/register/register.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ResponseInterceptor} from "./services/auth/response-interceptor.service";
import {registerLocaleData} from "@angular/common";
import localePL from '@angular/common/locales/pl';
import {SlotsComponent} from './pages/game/slots/slots.component';
import {AuthInterceptor} from "./services/auth/auth-interceptor.service";
import {SlotsResolver} from "./pages/game/slots/slots-resolver";
import {RouletteComponent} from './pages/game/roulette/roulette.component';
import {RouletteResolver} from "./pages/game/roulette/roulette-resolver";
import {TwentyOneComponent} from './pages/game/twenty-one/twenty-one.component';
import {TwentyOneResolver} from "./pages/game/twenty-one/twenty-one.resolver";
import {AccountDetailsResolver} from "./pages/account/account-details/account-details-resolver.service";
import {ErrorBoxComponent} from './standalone-components/error-box/error-box.component';
import {ErrorBoxService} from "./standalone-components/error-box/error-box.service";

registerLocaleData(localePL);

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    SlotsComponent,
    RouletteComponent,
    TwentyOneComponent,
    ErrorBoxComponent
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
    { provide: HTTP_INTERCEPTORS, useClass: ResponseInterceptor, multi: true },
    FormBuilder,
    SlotsResolver,
    RouletteResolver,
    TwentyOneResolver,
    ErrorBoxService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
