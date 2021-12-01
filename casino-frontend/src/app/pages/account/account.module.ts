import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AccountRoutingModule} from "./account-routing.module";
import {AccountDetailsComponent} from "./account-details/account-details.component";
import {DepositComponent} from "./deposit/deposit.component";
import {EditPasswordComponent} from "./edit-password/edit-password.component";
import {NoMoneyComponent} from "./no-money/no-money.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AccountDetailsResolver} from "./account-details/account-details-resolver.service";
import {AccountDetailsService} from "./account-details/account-details.service";
import {DepositService} from "./deposit/deposit.service";

@NgModule({
  declarations: [
    AccountDetailsComponent,
    DepositComponent,
    EditPasswordComponent,
    NoMoneyComponent
  ],
  imports: [
    CommonModule,
    AccountRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    AccountDetailsResolver,
    AccountDetailsService,
    DepositService
  ]
})
export class AccountModule { }
