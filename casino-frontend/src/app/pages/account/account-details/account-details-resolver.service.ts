import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {CasinoUserDto} from "../../../model/data";
import {ConnectionService} from "../../../services/connection/connection.service";

@Injectable()
export class AccountDetailsResolver implements Resolve<CasinoUserDto> {

  constructor(private connectionService: ConnectionService) {}

  resolve(route: ActivatedRouteSnapshot): Promise<CasinoUserDto> {
    return this.connectionService.getCasinoUser().toPromise();
  }
}
