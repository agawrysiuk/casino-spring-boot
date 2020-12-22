import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      retypedPassword: ['', Validators.required],
      email: ['', Validators.required, Validators.email]
    });
  }

  ngOnInit(): void {
  }

  register() {

  }

  checkRetypedPassword() {
    if(this.form.controls.password.value !== this.form.controls.retypedPassword.value) {
      this.form.controls.retypedPassword.setErrors({'invalid': true});
    } else {
      this.form.controls.retypedPassword.setErrors(null);
    }
  }

}
