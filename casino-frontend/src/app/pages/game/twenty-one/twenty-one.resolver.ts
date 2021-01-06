import {Injectable} from "@angular/core";
import {TwentyOneResponseDto} from "../../../model/game";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {GameService} from "../../../services/game.service";

@Injectable()
export class TwentyOneResolver implements Resolve<TwentyOneResponseDto> {

  constructor(private game: GameService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.game.getInitialTwentyOne();
  }
}
