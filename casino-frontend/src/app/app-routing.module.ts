import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./pages/auth/login/login.component";
import {RegisterComponent} from "./pages/auth/register/register.component";
import {AuthGuardService} from "./services/auth/auth-guard.service";
import {SlotsComponent} from "./pages/game/slots/slots.component";
import {SlotsResolver} from "./pages/game/slots/slots-resolver";
import {RouletteComponent} from "./pages/game/roulette/roulette.component";
import {RouletteResolver} from "./pages/game/roulette/roulette-resolver";
import {TwentyOneComponent} from "./pages/game/twenty-one/twenty-one.component";
import {TwentyOneResolver} from "./pages/game/twenty-one/twenty-one.resolver";

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'account',
    canActivate: [AuthGuardService],
    loadChildren: () => import('./pages/account/account.module').then(module => module.AccountModule)
  },
  {
    path: 'slots',
    component: SlotsComponent,
    canActivate: [AuthGuardService],
    resolve: { slots : SlotsResolver }
  },
  {
    path: 'roulette',
    component: RouletteComponent,
    canActivate: [AuthGuardService],
    resolve: { roulette : RouletteResolver }
  },
  {
    path: 'twenty-one',
    component: TwentyOneComponent,
    canActivate: [AuthGuardService],
    resolve: { twentyOne : TwentyOneResolver }
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
