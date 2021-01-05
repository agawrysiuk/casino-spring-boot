import {Injectable, RendererFactory2} from "@angular/core";
import {Router} from "@angular/router";
import {TokenStorageService} from "./auth/token-storage.service";
import {interval} from "rxjs";
import {takeWhile} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class InactivityService {

  lastInteraction: Date = new Date();
  definedInactivityPeriod = 10000;

  constructor(private rendererFactory2: RendererFactory2,
    private tokenStorage: TokenStorageService,
    private router: Router) {
    const renderer = this.rendererFactory2.createRenderer(null, null);
    renderer.listen('document', 'mousemove', () => {
      this.lastInteraction = new Date();
    });
  }

  inactivityCheck() {
    return interval(1000)
      .pipe(
        takeWhile(() => {
          const now = new Date();
          if ((now.getTime() - this.lastInteraction.getTime()) > this.definedInactivityPeriod) {
            console.log('Inactive for ' + this.definedInactivityPeriod / 1000 + 's, logging out!');
            this.tokenStorage.signOut();
          }
          return (now.getTime() - this.lastInteraction.getTime()) < this.definedInactivityPeriod;
        })
      );
  }

  start(): void {
    console.log('Starting inactivity check!');
    this.inactivityCheck().subscribe();
  }
}
