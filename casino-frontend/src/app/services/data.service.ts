import { Injectable } from '@angular/core';
import {CasinoUserDto, EditCasinoUserRequest} from "../model/data";
import {ConnectionService} from "./connection/connection.service";
import {tap} from "rxjs/operators";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private user: CasinoUserDto = {} as CasinoUserDto;

  constructor(private connection: ConnectionService) { }

  public getCasinoUser(): void {
    this.connection.getCasinoUser().subscribe(response => this.user = {...response});
  }

  public get casinoUser(): CasinoUserDto {
    return this.user;
  }

  public setCasinoUser(editedUser: EditCasinoUserRequest): Observable<CasinoUserDto> {
    return this.connection.editCasinoUser(editedUser)
      .pipe(tap(response => this.user = response));
  }

  updateBalance(balance: number): void {
    this.user.balance = balance;
  }

  clear(): void {
    this.user = null;
  }
}
