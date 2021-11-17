import {Injectable} from "@angular/core";
import {RouletteResponseDto} from "../../../model/game";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {GameService} from "../../../services/game.service";

@Injectable()
export class RouletteResolver implements Resolve<RouletteResponseDto> {

  constructor(private game: GameService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.game.getInitialRoulette();
  }
}
