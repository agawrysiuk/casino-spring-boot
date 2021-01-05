import { Component } from '@angular/core';
import {TokenStorageService} from "./services/auth/token-storage.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private tokenStorage: TokenStorageService) {}

  isLoggedIn(): boolean {
    return !!this.tokenStorage.getToken();
  }

  logout() {
    this.tokenStorage.signOut();
  }
}
