import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../../services/auth/token-storage.service";
import {DataService} from "../../../services/data.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {checkPasswordMatch} from "../../../utils/form-utils";
import {PasswordDto} from "../../../model/data";

@Component({
  selector: 'app-edit-password',
  templateUrl: './edit-password.component.html',
  styleUrls: ['./edit-password.component.scss']
})
export class EditPasswordComponent implements OnInit {

  passwordForm: FormGroup
  errorMessage: string;
  error: boolean = false;
  success: boolean = false;
  buttonClickable: boolean = true;

  constructor(private tokenStorage: TokenStorageService,
              private data: DataService,
              private fb: FormBuilder,
              private route: Router) {
    this.passwordForm = this.fb.group({
      oldPassword: ['', Validators.required],
      password: ['', Validators.required],
      matchingPassword: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  changePassword() {
    if(this.passwordForm.valid) {
      this.error = false;
      const passwordDto: PasswordDto = {
        username: this.tokenStorage.getUser().username,
        oldPassword: this.passwordForm.value.oldPassword,
        password: this.passwordForm.value.password,
        matchingPassword: this.passwordForm.value.matchingPassword
      };
      this.data.editPassword(passwordDto)
        .then(() => {
          this.success = true;
          setTimeout(() => this.route.navigate(['account']), 1000);
        })
        .catch(error => {
          this.error = true;
          this.errorMessage = error.error;
        });
    }
  }

  checkRetypedPassword() {
    checkPasswordMatch(this.passwordForm);
  }
}
