import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./modules/home/home.component";
import {LoginComponent} from "./modules/auth/login/login.component";
import {RegisterComponent} from "./modules/auth/register/register.component";
import {AuthGuardService} from "./services/auth/auth-guard.service";
import {AccountComponent} from "./modules/account/account.component";
import {EditPasswordComponent} from "./modules/account/edit-password/edit-password.component";

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'account',
    component: AccountComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'edit-password',
    component: EditPasswordComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
