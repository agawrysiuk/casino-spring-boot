import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {CasinoUserDto} from "../../../model/data";
import {SessionService} from "../../../services/session.service";
import {DataService} from "../../../services/data.service";
import {TokenStorageService} from "../../../services/auth/token-storage.service";

@Injectable()
export class AccountResolver implements Resolve<CasinoUserDto> {

  constructor(private session: SessionService,
              private data: DataService) {}

  resolve(route: ActivatedRouteSnapshot): Promise<CasinoUserDto> {
    return new Promise((resolve) => resolve.apply(this.data.casinoUser) as Promise<CasinoUserDto>);
  }
}
