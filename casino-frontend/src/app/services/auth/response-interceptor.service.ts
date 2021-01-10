import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {Router} from "@angular/router";
import {SessionService} from "../session.service";
import {ErrorBoxService} from "../../standalone-components/error-box/error-box.service";

@Injectable()
export class ResponseInterceptor implements HttpInterceptor {
  constructor(private session: SessionService,
              private router: Router,
              private errorModal: ErrorBoxService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("Response called")
    return next.handle(req).pipe(
      catchError(error => {
        if(error.status == 469) {
          this.router.navigate(['no-money']);
        } else {
          this.errorModal.showError("Error while loading page. For security reasons, if you were logged in, we will log you out.")
          this.session.logout();
        }
        return of(error);
      })
    );
  }
}
