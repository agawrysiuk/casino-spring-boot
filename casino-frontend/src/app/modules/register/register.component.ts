import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserDto} from "../../model/userDto";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  userDto: UserDto = {} as UserDto;

  constructor(private fb: FormBuilder) {
    this.form = fb.group({
      username: [this.userDto.username, Validators.required],
      password: [this.userDto.password, Validators.required],
      matchingPassword: [this.userDto.matchingPassword, Validators.required],
      email: [this.userDto.email, Validators.required, Validators.email]
    });
  }

  ngOnInit(): void {
  }

  register() {
    if(this.form.valid) {

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
