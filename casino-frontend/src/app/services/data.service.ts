import { Injectable } from '@angular/core';
import {CasinoUserDto} from "../model/data";
import {ConnectionService} from "./connection/connection.service";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private user: CasinoUserDto = {} as CasinoUserDto;

  constructor(private connection: ConnectionService) { }

  public downloadCasinoUser(username: string): Promise<CasinoUserDto> {
    return this.connection.getCasinoUser(username)
      .then(response => {
        this.user = response as CasinoUserDto;
        return this.user;
      });
  }

  public getCasinoUser(): CasinoUserDto {
    return this.user;
  }

  public setCasinoUser(editedUser: CasinoUserDto) {
    return this.connection.editCasinoUser(editedUser)
      .then(response => {
        this.user = response;
      });
  }

  updateBalance(balance: number) {
    this.user.balance = balance;
  }

  isDownloaded() {
    return !!this.user.nickname;
  }

  clear() {
    this.user = null;
  }
}
