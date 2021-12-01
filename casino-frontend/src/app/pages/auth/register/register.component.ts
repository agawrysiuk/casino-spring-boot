import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../services/auth/auth.service";
import {CustomValidators} from "../../../utils/form-utils";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  errorMessage: string | undefined;

  constructor(private fb: FormBuilder,
              private auth: AuthService) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      username: ['', Validators.required, Validators.minLength(4)],
      password: ['', Validators.required],
      matchingPassword: ['', Validators.required],
      email: ['', Validators.required, Validators.email]
    }, {validators: CustomValidators.matchPassword});
  }

  register(): void {
    if(this.form.valid) {
      this.auth.register(this.form.value).subscribe(
        () => null,
        error => this.errorMessage = error.error.message
    );
    }
  }

}
