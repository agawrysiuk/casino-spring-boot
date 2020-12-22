import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginRequest} from "../../model/data";
import {AuthService} from "../../services/auth.service";
import {TokenStorageService} from "../../services/token-storage.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  loginRequest: LoginRequest = {} as LoginRequest;
  loginFailed: boolean = false;

  constructor(private fb: FormBuilder,
              private auth: AuthService,
              private tokenStorage: TokenStorageService,
              private router: Router) {
    this.form = fb.group({
      username: [this.loginRequest.username, Validators.required],
      password: [this.loginRequest.password, Validators.required]
    })
  }

  ngOnInit(): void {
  }

  login() {
    if(this.form.valid) {
      this.auth.login(this.loginRequest)
        .then(data => {
          this.tokenStorage.saveToken(data.accessToken);
          this.tokenStorage.saveUser(data);
          this.router.navigate(['home']);
        })
        .catch(err => this.loginFailed = true);
    }
  }

}
