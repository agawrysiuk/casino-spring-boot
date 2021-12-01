import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginRequest} from "../../../model/data";
import {AuthService} from "../../../services/auth/auth.service";
import {TokenStorageService} from "../../../services/auth/token-storage.service";
import {ActivatedRoute, Router} from "@angular/router";
import {InactivityService} from "../../../services/inactivity.service";
import {DataService} from "../../../services/data.service";
import {SessionService} from "../../../services/session.service";
import {catchError} from "rxjs/operators";

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
              private sessionService: SessionService,
              private router: Router,
              private inactivityService: InactivityService,
              private data: DataService) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  login(): void {
    if (this.form.valid) {
      this.auth.login(this.form.value).subscribe(
        data => {
          this.saveData(data);
          this.inactivityService.start();
          this.router.navigate(['home']);
        },
        catchError => this.loginFailed = true);
    }
  }

  private saveData(data: LoginRequest): void {
    this.sessionService.saveData(data);
    this.data.getCasinoUser();
  }

}
