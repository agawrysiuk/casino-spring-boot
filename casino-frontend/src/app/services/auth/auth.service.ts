import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginRequest, UserDto} from "../../model/data";
import {httpOptions} from "../connection/connection-utils";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly API_ENDPOINT = environment.endpoint + environment.apiVersion;

  constructor(private http: HttpClient) { }

  public login(loginRequest: LoginRequest): Observable<any> {
    return this.http.put(this.API_ENDPOINT + '/user/login', loginRequest, httpOptions);
  }

  public register(userDto: UserDto): Observable<any> {
    return this.http.put(this.API_ENDPOINT + '/user/register', userDto, httpOptions);
  }
}
