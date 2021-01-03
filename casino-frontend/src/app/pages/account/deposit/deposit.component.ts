import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../../services/auth/token-storage.service";
import {DataService} from "../../../services/data.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {CasinoUserDto} from "../../../model/data";

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.scss']
})
export class DepositComponent implements OnInit {

  error: string;
  depositForm: FormGroup;

  constructor(private tokenStorage: TokenStorageService,
              private data: DataService,
              private fb: FormBuilder,
              private route: Router) {

    this.depositForm = this.fb.group({
      firstName: ['', Validators.required],
      surname: ['', Validators.required],
      cardNumber: ['', Validators.required],
      expiryDate: ['', Validators.required],
      ccv: ['', [Validators.required,Validators.pattern(new RegExp('[0-9]'))]],
      depositAmount: ['', Validators.required],
    })
  }

  ngOnInit(): void {
  }

  deposit() {
    if(this.depositForm.valid) {
      this.error = undefined;
      this.data.deposit(this.depositForm.value)
        .then(() => setTimeout(() => this.route.navigate(['/account'])))
        .catch(() => this.error = "Error");
    }
  }
}
