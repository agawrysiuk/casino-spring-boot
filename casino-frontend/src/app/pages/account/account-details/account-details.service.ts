import { Injectable } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CasinoUserDto} from "../../../model/data";
import {DataService} from "../../../services/data.service";
import {BehaviorSubject, Observable} from "rxjs";
import {AccountDetailsPageState} from "./account-details.component";

@Injectable()
export class AccountDetailsService {

  private stateSubject: BehaviorSubject<AccountDetailsPageState>;

  constructor(private fb: FormBuilder,
              private data: DataService) {
  }

  getState(casinoUser: CasinoUserDto): Observable<AccountDetailsPageState> {
    const defaultState: AccountDetailsPageState = {
      casinoUser: casinoUser,
      editingInfo: false,
      showError: false
    };
    this.stateSubject = new BehaviorSubject<AccountDetailsPageState>(defaultState);
    return this.stateSubject.asObservable();
  }

  createEditForm(): FormGroup {
    const casinoUser = this.stateSubject.getValue().casinoUser;
    this.stateSubject.next({casinoUser: casinoUser, editingInfo: true, showError: false});
    return this.fb.group({
      firstName: [casinoUser.firstName, Validators.required],
      secondName: [casinoUser.secondName, Validators.required],
      birthDate: [casinoUser.birthDate, Validators.required],
      country: [casinoUser.country, Validators.required]
    });
  }

  saveEditForm(form: FormGroup): void {
    if (form.valid) {
      this.saveNewInfo(form);
    } else {
      this.setError();
    }
  }

  private saveNewInfo(form: FormGroup): void {
    this.data.setCasinoUser({
      firstName: form.value.firstName,
      secondName: form.value.secondName,
      birthDate: form.value.birthDate,
      country: form.value.country
    }).subscribe(
      response =>
        this.stateSubject.next({
          casinoUser: response,
          editingInfo: false,
          showError: false
        }),
      catchError => this.setError()
    );
  }

  private setError(): void {
    this.stateSubject.next({
      casinoUser: this.stateSubject.getValue().casinoUser,
      editingInfo: true,
      showError: true
    });
  }
}
