import {Injectable} from '@angular/core';
import {
  RouletteRequestDto,
  RouletteResponseDto,
  SlotsDto,
  TwentyOneRequestDto,
  TwentyOneResponseDto
} from "../model/game";
import {GameConnectionService} from "./connection/game-connection.service";
import {DataService} from "./data.service";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private gameConnection: GameConnectionService,
              private data: DataService) {
  }

  public getInitialSlots(): Promise<SlotsDto> {
    return this.gameConnection.getInitialSlots().then(slots => {
      this.data.updateBalance(slots.balance);
      return slots;
    });
  }

  public postSlots(): Promise<SlotsDto> {
    return this.gameConnection.postSlots().then(slots => {
      this.data.updateBalance(slots.balance);
      return slots;
    });
  }

  public getInitialRoulette(): Promise<RouletteResponseDto> {
    return this.gameConnection.getInitialRoulette().then(roulette => {
      this.data.updateBalance(roulette.balance);
      return roulette;
    });
  }

  public postRoulette(rouletteRequestDto: RouletteRequestDto): Promise<RouletteResponseDto> {
    return this.gameConnection.postRoulette(rouletteRequestDto).then(roulette => {
      this.data.updateBalance(roulette.balance);
      return roulette;
    });
  }

  getInitialTwentyOne(): Promise<TwentyOneResponseDto>{
    return this.gameConnection.getInitialTwentyOne().then(twentyOne => {
      this.data.updateBalance(twentyOne.balance);
      return twentyOne;
    });
  }

  postTwentyOne(request: TwentyOneRequestDto): Promise<TwentyOneResponseDto> {
    return this.gameConnection.postTwentyOne(request).then(twentyOne => {
      this.data.updateBalance(twentyOne.balance);
      return twentyOne;
    });
  }
}
