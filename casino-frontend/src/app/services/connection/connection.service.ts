import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API, httpOptions} from "./connection-utils";
import {CasinoUserDto, CreditCardObject, EditCasinoUserRequest, EditPasswordRequest} from "../../model/data";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  constructor(private http: HttpClient) { }

  public getCasinoUser(): Observable<CasinoUserDto> {
    return this.http.get<CasinoUserDto>(API + "/casino-user");
  }

  public editCasinoUser(editedUser: EditCasinoUserRequest): Observable<CasinoUserDto> {
    return this.http.post<CasinoUserDto>(API + "/casino-user", editedUser);
  }

  public editPassword(editedPassword: EditPasswordRequest): Observable<any> {
    return this.http.post<any>(API + "/edit-password", editedPassword, httpOptions);
  }

  public deposit(deposit: CreditCardObject): Observable<any> {
    return this.http.post<any>(API + "/deposit", deposit, httpOptions);
  }
}
