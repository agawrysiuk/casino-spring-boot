import { Injectable } from '@angular/core';
import {CanActivate, Router} from "@angular/router";
import {JwtHelperService} from "@auth0/angular-jwt";
import {TokenStorageService} from "./token-storage.service";

const jwtHelper = new JwtHelperService();

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private tokenStorage: TokenStorageService,
              private router: Router) { }

  canActivate(): boolean {
    if (!this.isAuthenticated()) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }

  private isAuthenticated() {
    const token = this.tokenStorage.getToken();
    return token ? !jwtHelper.isTokenExpired(token) : false;
  }
}
