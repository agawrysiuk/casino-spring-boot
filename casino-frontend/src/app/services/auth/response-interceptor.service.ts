import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {Observable, of, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {Router} from "@angular/router";

@Injectable()
export class ResponseInterceptor implements HttpInterceptor {
  constructor(private token: TokenStorageService,
              private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("Response called")
    return next.handle(req).pipe(
      catchError(error => {
        if(error.status == 469) {
          this.router.navigate(['no-money']);
        }
        return of(error);
      })
    );
  }
}
