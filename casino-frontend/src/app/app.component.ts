import { Component } from '@angular/core';
import {TokenStorageService} from "./services/auth/token-storage.service";
import {Router} from "@angular/router";
import {DataService} from "./services/data.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private tokenStorage: TokenStorageService,
              private router: Router) {}

  isLoggedIn(): boolean {
    return !!this.tokenStorage.getToken();
  }

  logout() {
    this.tokenStorage.signOut();
    this.router.navigate(['login']);
  }
}
