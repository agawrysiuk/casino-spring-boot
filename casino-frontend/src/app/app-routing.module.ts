import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./pages/auth/login/login.component";
import {RegisterComponent} from "./pages/auth/register/register.component";
import {AuthGuardService} from "./services/auth/auth-guard.service";
import {AccountComponent} from "./pages/account/account/account.component";
import {EditPasswordComponent} from "./pages/account/edit-password/edit-password.component";
import {DepositComponent} from "./pages/account/deposit/deposit.component";
import {SlotsComponent} from "./pages/game/slots/slots.component";
import {NoMoneyComponent} from "./pages/account/no-money/no-money.component";
import {SlotsResolver} from "./pages/game/slots/slots-resolver";

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
    path: 'deposit',
    component: DepositComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'slots',
    component: SlotsComponent,
    canActivate: [AuthGuardService],
    resolve: { slots : SlotsResolver }
  },
  {
    path: 'no-money',
    component: NoMoneyComponent,
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
