import { Injectable } from '@angular/core';
import {TokenStorageService} from "./auth/token-storage.service";
import {Router} from "@angular/router";
import {DataService} from "./data.service";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private tokenStorage: TokenStorageService,
              private data: DataService,
              private router: Router) {}

  isLoggedIn(): boolean {
    return !!this.tokenStorage.getToken();
  }

  logout() {
    this.tokenStorage.signOut();
    this.data.clear();
    this.router.navigate(['login']);
  }
}
