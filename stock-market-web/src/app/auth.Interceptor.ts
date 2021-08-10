import {Injectable} from "@angular/core";
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler, HttpHeaders,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";
import {Observable} from "rxjs";
import {AppService} from "./app.service";
import {Router} from "@angular/router";
import {tap} from "rxjs/operators";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private appService: AppService,
              private router: Router) {
  }

  intercept(request: HttpRequest<any>,
            next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.appService.getToken();
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    return next.handle(request)
      .pipe(tap((res: HttpResponse<any>) => {
          const headers = res.headers;
          if (headers instanceof HttpHeaders && request.url.includes("/auth/login")) {
            const token = headers.get('Authorization');
            this.appService.setToken(token);
          }
          return;
        },
        (err: any) => {
          if (err instanceof HttpErrorResponse) {
            if (err.status !== 401) {
              return;
            }
            this.appService.clearToken();
            this.router.navigateByUrl('/login');
          }
        }));
  }
}
