import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "./services/auth/token-storage.service";
import {SessionService} from "./services/session.service";
import {ErrorBoxComponent} from "./standalone-components/error-box/error-box.component";
import {ErrorBoxService} from "./standalone-components/error-box/error-box.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  loggedIn: Observable<boolean>;

  constructor(private session: SessionService) {}

  ngOnInit(): void {
    this.loggedIn = this.session.state;
  }

  isLoggedIn(): boolean {
    return this.session.isLoggedIn();
  }

  logout(): void {
    this.session.logout();
  }
}
