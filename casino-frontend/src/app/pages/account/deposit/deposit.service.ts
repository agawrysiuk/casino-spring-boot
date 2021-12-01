import {Injectable} from '@angular/core';
import {ConnectionService} from "../../../services/connection/connection.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Observable, Subject} from "rxjs";

@Injectable()
export class DepositService {

  private errorSubject: Subject<string> = new Subject<string>();

  constructor(private data: ConnectionService,
              private fb: FormBuilder,
              private route: Router) {
  }

  get errorState(): Observable<string> {
    return this.errorSubject.asObservable();
  }

  createForm(): FormGroup {
    return this.fb.group({
      firstName: ['', Validators.required],
      surname: ['', Validators.required],
      cardNumber: ['', Validators.required],
      expiryDate: ['', Validators.required],
      ccv: ['', [Validators.required, Validators.pattern(new RegExp('[0-9]'))]],
      depositAmount: ['', Validators.required],
    });
  }

  deposit(depositForm: FormGroup): void {
    if (depositForm.valid) {
      this.errorSubject.next(null);
      this.data.deposit(depositForm.value).subscribe(
        () => setTimeout(() => this.route.navigate(['/account'])),
        catchError => this.errorSubject.next("Error")
      );
    } else {
      this.errorSubject.next("Invalid card details");
    }
  }
}
