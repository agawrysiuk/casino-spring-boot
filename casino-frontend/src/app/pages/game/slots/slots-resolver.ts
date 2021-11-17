import {Injectable} from "@angular/core";
import {SlotsDto} from "../../../model/game";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {GameService} from "../../../services/game.service";

@Injectable()
export class SlotsResolver implements Resolve<SlotsDto> {

  constructor(private game: GameService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.game.getInitialSlots();
  }
}
