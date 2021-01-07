import {Injectable, RendererFactory2} from "@angular/core";
import {interval} from "rxjs";
import {takeWhile} from "rxjs/operators";
import {SessionService} from "./session.service";

@Injectable({
  providedIn: 'root'
})
export class InactivityService {

  lastInteraction: Date = new Date();
  definedInactivityPeriod = 10 * 60 * 1000;

  constructor(private rendererFactory2: RendererFactory2,
              private session: SessionService) {
    const renderer = this.rendererFactory2.createRenderer(null, null);
    renderer.listen('document', 'mousemove', () => {
      this.lastInteraction = new Date();
    });
  }

  public start(): void {
    console.log('Starting inactivity check!');
    this.inactivityCheck().subscribe();
  }

  private inactivityCheck() {
    return interval(1000)
      .pipe(
        takeWhile(() => {
          const now = new Date();
          if ((now.getTime() - this.lastInteraction.getTime()) > this.definedInactivityPeriod) {
            console.log('Inactive for ' + this.definedInactivityPeriod / 1000 + 's, logging out!');
            this.session.logout();
          }
          return (now.getTime() - this.lastInteraction.getTime()) < this.definedInactivityPeriod;
        })
      );
  }
}
