import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AccountDetailsComponent} from "./account-details/account-details.component";
import {AuthGuardService} from "../../services/auth/auth-guard.service";
import {AccountDetailsResolver} from "./account-details/account-details-resolver.service";
import {EditPasswordComponent} from "./edit-password/edit-password.component";
import {DepositComponent} from "./deposit/deposit.component";
import {NoMoneyComponent} from "./no-money/no-money.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'details'
  },
  {
    path: 'details',
    component: AccountDetailsComponent,
    canActivate: [AuthGuardService],
    resolve: { account : AccountDetailsResolver }
  },
  {
    path: 'deposit',
    component: DepositComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'edit-password',
    component: EditPasswordComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'no-money',
    component: NoMoneyComponent,
    canActivate: [AuthGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule { }
