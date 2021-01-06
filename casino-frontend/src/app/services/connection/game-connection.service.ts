import { Injectable } from '@angular/core';
import {API, httpOptions} from "./connection-utils";
import {HttpClient} from "@angular/common/http";
import {RouletteRequestDto, RouletteResponseDto, SlotsDto} from "../../model/game";

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

  public getInitialRoulette(): Promise<RouletteResponseDto> {
    return this.http.get(API + "/roulette", httpOptions).toPromise() as Promise<RouletteResponseDto>;
  }

  public postRoulette(rouletteRequestDto: RouletteRequestDto): Promise<RouletteResponseDto> {
    return this.http.post(API + "/roulette", rouletteRequestDto, httpOptions).toPromise() as Promise<RouletteResponseDto>;
  }
}
