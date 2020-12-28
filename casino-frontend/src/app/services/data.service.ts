import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API} from "./connection-utils";
import {CasinoUserDto} from "../model/data";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  public getCasinoUser(username: string): Promise<CasinoUserDto> {
    return this.http.get(API + "/casino-user?name=" + username).toPromise() as Promise<CasinoUserDto>;
  }

}
