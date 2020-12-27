import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LoginRequest, UserDto} from "../model/data";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private API = 'http://localhost:8080/api';
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  public login(loginRequest: LoginRequest): Promise<any> {
    return this.http.post(this.API + '/login', loginRequest, this.httpOptions).toPromise();
  }

  public register(userDto: UserDto): Promise<any> {
    return this.http.post(this.API + '/register', userDto, this.httpOptions).toPromise();
  }
}
