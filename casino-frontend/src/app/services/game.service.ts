import {Injectable} from '@angular/core';
import {RouletteRequestDto, RouletteResponseDto, SlotsDto} from "../model/game";
import {API, httpOptions} from "./connection/connection-utils";
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
}
