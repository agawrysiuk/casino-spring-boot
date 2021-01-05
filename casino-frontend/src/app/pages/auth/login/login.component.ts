import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginRequest} from "../../../model/data";
import {AuthService} from "../../../services/auth/auth.service";
import {TokenStorageService} from "../../../services/auth/token-storage.service";
import {ActivatedRoute, Router} from "@angular/router";
import {InactivityService} from "../../../services/inactivity.service";
import {DataService} from "../../../services/data.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  loginFailed: boolean = false;

  constructor(private fb: FormBuilder,
              private auth: AuthService,
              private tokenStorage: TokenStorageService,
              private router: Router,
              private inactivityService: InactivityService,
              private data: DataService) {
    this.form = fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  ngOnInit(): void {
  }

  login() {
    if(this.form.valid) {
      this.auth.login(this.form.value)
        .then(data => {
          this.saveData(data);
          this.inactivityService.start();
          this.router.navigate(['home']);
        })
        .catch(err => this.loginFailed = true);
    }
  }

  private saveData(data) {
    this.tokenStorage.saveToken(data.token);
    this.tokenStorage.saveUser(data);
    this.data.downloadCasinoUser(data.username);
  }

}
