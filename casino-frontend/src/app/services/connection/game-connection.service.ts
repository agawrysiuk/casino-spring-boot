import { Injectable } from '@angular/core';
import {httpOptions} from "./connection-utils";
import {HttpClient} from "@angular/common/http";
import {
  RouletteRequestDto,
  RouletteResponseDto,
  SlotsDto,
  TwentyOneRequestDto,
  TwentyOneResponseDto
} from "../../model/game";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class GameConnectionService {

  private readonly API_ENDPOINT = environment.endpoint;

  constructor(private http: HttpClient) { }

  public getInitialSlots(): Promise<SlotsDto> {
    return this.http.get(this.API_ENDPOINT + "/game/slots", httpOptions).toPromise() as Promise<SlotsDto>;
  }

  public postSlots():Promise<SlotsDto> {
    return this.http.post(this.API_ENDPOINT + "/game/slots", httpOptions).toPromise() as Promise<SlotsDto>;
  }

  public getInitialRoulette(): Promise<RouletteResponseDto> {
    return this.http.get(this.API_ENDPOINT + "/game/roulette", httpOptions).toPromise() as Promise<RouletteResponseDto>;
  }

  public postRoulette(rouletteRequestDto: RouletteRequestDto): Promise<RouletteResponseDto> {
    return this.http.post(this.API_ENDPOINT + "/game/roulette", rouletteRequestDto, httpOptions).toPromise() as Promise<RouletteResponseDto>;
  }

  public getInitialTwentyOne(): Promise<TwentyOneResponseDto> {
    return this.http.get(this.API_ENDPOINT + "/game/twenty-one", httpOptions).toPromise() as Promise<TwentyOneResponseDto>;
  }

  postTwentyOne(requestCode: TwentyOneRequestDto): Promise<TwentyOneResponseDto> {
    return this.http.post(this.API_ENDPOINT + "/game/twenty-one", requestCode, httpOptions).toPromise() as Promise<TwentyOneResponseDto>;

  }
}
