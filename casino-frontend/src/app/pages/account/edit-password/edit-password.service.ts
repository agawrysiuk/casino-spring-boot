import {Injectable} from '@angular/core';
import {ConnectionService} from "../../../services/connection/connection.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {CustomValidators} from "../../../utils/form-utils";
import {EditPasswordRequest} from "../../../model/data";
import {BehaviorSubject, Observable} from "rxjs";
import {EditPasswordPageState} from "./edit-password.component";

@Injectable({
  providedIn: 'root'
})
export class EditPasswordService {

  private readonly editPasswordPageState: BehaviorSubject<EditPasswordPageState> = new BehaviorSubject<EditPasswordPageState>({
    buttonClickable: true,
    error: false,
    errorMessage: '',
    success: false
  });

  constructor(private data: ConnectionService,
              private fb: FormBuilder,
              private route: Router) {
  }

  get state(): Observable<EditPasswordPageState> {
    return this.editPasswordPageState.asObservable();
  }

  createForm(): FormGroup {
    return this.fb.group({
      oldPassword: ['', Validators.required],
      password: ['', Validators.required],
      matchingPassword: ['', Validators.required]
    }, {validators: CustomValidators.matchPassword});
  }

  changePassword(passwordForm: FormGroup): void {
    if (passwordForm.valid) {
      this.editPasswordPageState.next({...this.editPasswordPageState.getValue(), error: false})
      const passwordDto: EditPasswordRequest = {
        oldPassword: passwordForm.value.oldPassword,
        password: passwordForm.value.password,
        matchingPassword: passwordForm.value.matchingPassword
      };
      this.data.editPassword(passwordDto)
        .subscribe(
          () => {
            this.editPasswordPageState.next({...this.editPasswordPageState.getValue(), success: true})
            setTimeout(() => this.route.navigate(['account']), 1000);
          },
          catchError => {
            this.editPasswordPageState.next({
              ...this.editPasswordPageState.getValue(),
              error: true,
              errorMessage: catchError.error
            })
          });
    }
  }
}
