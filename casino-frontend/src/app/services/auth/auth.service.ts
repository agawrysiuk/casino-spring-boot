import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginRequest, UserDto} from "../../model/data";
import {httpOptions} from "../connection/connection-utils";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly API_ENDPOINT = environment.endpoint;

  constructor(private http: HttpClient) { }

  public login(loginRequest: LoginRequest): Promise<any> {
    return this.http.put(this.API_ENDPOINT + '/user/login', loginRequest, httpOptions).toPromise();
  }

  public register(userDto: UserDto): Promise<any> {
    return this.http.put(this.API_ENDPOINT + '/user/register', userDto, httpOptions).toPromise();
  }
}
