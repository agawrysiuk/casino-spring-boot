import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../../services/auth/token-storage.service";
import {ConnectionService} from "../../../services/connection/connection.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.scss']
})
export class DepositComponent implements OnInit {

  error: string;
  depositForm: FormGroup;

  constructor(private tokenStorage: TokenStorageService,
              private data: ConnectionService,
              private fb: FormBuilder,
              private route: Router) {

    this.depositForm = this.fb.group({
      firstName: ['', Validators.required],
      surname: ['', Validators.required],
      cardNumber: ['', Validators.required],
      expiryDate: ['', Validators.required],
      ccv: ['', [Validators.required, Validators.pattern(new RegExp('[0-9]'))]],
      depositAmount: ['', Validators.required],
    })
  }

  ngOnInit(): void {
  }

  deposit() {
    if (this.depositForm.valid) {
      this.error = undefined;
      this.data.deposit(this.depositForm.value)
        .subscribe(
          () => setTimeout(() => this.route.navigate(['/account'])),
          catchError => this.error = "Error"
        );
    }
  }
}
