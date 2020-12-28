import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginRequest, UserDto} from "../../model/data";
import {API, httpOptions} from "../connection-utils";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(loginRequest: LoginRequest): Promise<any> {
    return this.http.post(API + '/login', loginRequest, httpOptions).toPromise();
  }

  public register(userDto: UserDto): Promise<any> {
    return this.http.post(API + '/register', userDto, httpOptions).toPromise();
  }
}
