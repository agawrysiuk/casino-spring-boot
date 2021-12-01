import {Component, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {DepositService} from "./deposit.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.scss']
})
export class DepositComponent implements OnInit {

  error: Observable<string>;
  depositForm: FormGroup;

  constructor(private service: DepositService) {
  }

  ngOnInit(): void {
    this.error = this.service.errorState;
    this.depositForm = this.service.createForm();
  }

  deposit() {
    this.service.deposit(this.depositForm);
  }
}
