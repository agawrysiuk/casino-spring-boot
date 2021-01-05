import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API, httpOptions} from "./connection-utils";
import {CasinoUserDto, CreditCardObject, PasswordDto} from "../../model/data";

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  constructor(private http: HttpClient) { }

  public getCasinoUser(username: string): Promise<CasinoUserDto> {
    return this.http.get(API + "/casino-user?name=" + username).toPromise() as Promise<CasinoUserDto>;
  }

  public editCasinoUser(editedUser: CasinoUserDto): Promise<CasinoUserDto> {
    return this.http.post(API + "/casino-user", editedUser).toPromise() as Promise<CasinoUserDto>;
  }

  public editPassword(editedPassword: PasswordDto): Promise<any> {
    return this.http.post(API + "/edit-password", editedPassword, httpOptions).toPromise();
  }

  public deposit(deposit: CreditCardObject): Promise<any> {
    return this.http.post(API + "/deposit", deposit, httpOptions).toPromise();
  }
}