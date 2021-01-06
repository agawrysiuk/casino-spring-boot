import {Injectable} from "@angular/core";
import {SlotsDto} from "../../../model/game";
import {GameConnectionService} from "../../../services/connection/game-connection.service";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";

@Injectable()
export class SlotsResolver implements Resolve<SlotsDto> {

  constructor(private gameConnection: GameConnectionService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.gameConnection.getInitialSlots();
  }
}
