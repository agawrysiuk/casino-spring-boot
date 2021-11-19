import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {httpOptions} from "./connection-utils";
import {CasinoUserDto, CreditCardObject, EditCasinoUserRequest, EditPasswordRequest} from "../../model/data";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  private readonly API_ENDPOINT: string = environment.endpoint + environment.apiVersion;

  constructor(private http: HttpClient) { }

  public getCasinoUser(): Observable<CasinoUserDto> {
    return this.http.get<CasinoUserDto>(this.API_ENDPOINT + "/casino-user");
  }

  public editCasinoUser(editedUser: EditCasinoUserRequest): Observable<CasinoUserDto> {
    return this.http.put<CasinoUserDto>(this.API_ENDPOINT + "/casino-user", editedUser);
  }

  public editPassword(editedPassword: EditPasswordRequest): Observable<any> {
    return this.http.put<any>(this.API_ENDPOINT + "/user/edit-password", editedPassword, httpOptions);
  }

  public deposit(deposit: CreditCardObject): Observable<any> {
    return this.http.put<any>(this.API_ENDPOINT + "/user/deposit", deposit, httpOptions);
  }
}
