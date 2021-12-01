import { Injectable } from '@angular/core';
import {TokenStorageService} from "./auth/token-storage.service";
import {Router} from "@angular/router";
import {DataService} from "./data.service";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private loggedInSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private tokenStorage: TokenStorageService,
              private data: DataService,
              private router: Router) {}

  get state(): Observable<boolean> {
    return this.loggedInSubject.asObservable();
  }

  isLoggedIn(): boolean {
    return !!this.tokenStorage.getToken();
  }

  logout(): void {
    this.tokenStorage.signOut();
    this.data.clear();
    this.router.navigate(['login']);
  }

  saveData(data: any): void {
    this.tokenStorage.saveToken(data.token);
    this.tokenStorage.saveUser(data);
  }
}
