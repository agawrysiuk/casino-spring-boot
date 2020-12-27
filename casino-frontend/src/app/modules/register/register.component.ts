import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserDto} from "../../model/data";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  errorMessage: string | undefined;

  constructor(private fb: FormBuilder,
              private auth: AuthService) {
    this.form = fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      matchingPassword: ['', Validators.required],
      email: ['', Validators.required, Validators.email]
    });
  }

  ngOnInit(): void {
  }

  register() {
    if(this.form.valid) {
      this.auth.register(this.form.value)
        .then()
        .catch(error => this.errorMessage = error.error.message);
    }
  }

  checkRetypedPassword() {
    if(this.form.controls.password.value !== this.form.controls.retypedPassword.value) {
      this.form.controls.matchingPassword.setErrors({'invalid': true});
    } else {
      this.form.controls.matchingPassword.setErrors(null);
    }
  }

}
