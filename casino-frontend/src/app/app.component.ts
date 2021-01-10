import { Component } from '@angular/core';
import {TokenStorageService} from "./services/auth/token-storage.service";
import {SessionService} from "./services/session.service";
import {ErrorBoxComponent} from "./standalone-components/error-box/error-box.component";
import {ErrorBoxService} from "./standalone-components/error-box/error-box.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private session: SessionService) {}

  isLoggedIn(): boolean {
    return this.session.isLoggedIn();
  }

  logout() {
    this.session.logout();
  }
}
