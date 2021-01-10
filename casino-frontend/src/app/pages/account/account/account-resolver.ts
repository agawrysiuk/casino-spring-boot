import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {CasinoUserDto} from "../../../model/data";
import {SessionService} from "../../../services/session.service";
import {DataService} from "../../../services/data.service";
import {TokenStorageService} from "../../../services/auth/token-storage.service";

@Injectable()
export class AccountResolver implements Resolve<CasinoUserDto> {

  constructor(private session: SessionService,
              private data: DataService,
              private tokenStorageService: TokenStorageService) {}

  resolve(route: ActivatedRouteSnapshot) {
    if(this.session.isLoggedIn() && !this.data.isDownloaded()) {
      return this.data.downloadCasinoUser(this.tokenStorageService.getUser().username);
    } else {
      return new Promise((resolve) => resolve.apply(this.data.getCasinoUser())) as Promise<CasinoUserDto>;
    }
  }
}
