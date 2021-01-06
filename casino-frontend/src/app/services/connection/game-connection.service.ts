import { Injectable } from '@angular/core';
import {API, httpOptions} from "./connection-utils";
import {HttpClient} from "@angular/common/http";
import {SlotsDto} from "../../model/game";

@Injectable({
  providedIn: 'root'
})
export class GameConnectionService {

  constructor(private http: HttpClient) { }

  public getInitialSlots(): Promise<SlotsDto> {
    return this.http.get(API + "/slots", httpOptions).toPromise() as Promise<SlotsDto>;
  }

  public postSlots():Promise<SlotsDto> {
    return this.http.post(API + "/slots", httpOptions).toPromise() as Promise<SlotsDto>;
  }
}
