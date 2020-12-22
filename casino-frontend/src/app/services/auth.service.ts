import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserDto} from "../model/userDto";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private API = 'http://localhost:8080/api';
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  public login(username: string, password: string) {
    return this.http.post(this.API + '/register', {
      username: username,
      password: password
    }, this.httpOptions).toPromise();
  }

  public register(userDto: UserDto) {
    return this.http.post(this.API + '/register', userDto, this.httpOptions).toPromise();
  }
}
