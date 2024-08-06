/*
LA INTENCIÓN ERA USARLO YA QUE ES LO SUYO, PERO
SURGÍAN MUCHOS PROBLEMAS POR ESTO
CON LO QUE SE SIGUIÓ EL DESARROLLO SIN ELLO.



import { HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { LoginService } from "./login.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    constructor (private loginService:LoginService) {

    }

    //El interceptor es para modificar peticiones. En este caso estamos agregando una cabecera
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let authReq = req;
        const token = this.loginService.getToken();
        if(token != null){
            authReq = authReq.clone({
                setHeaders : {Authorization:`Bearer ${token}`}
            })
        }
        return next.handle(authReq);
    }
}

export const authInterceptorProviders = [
    {
        provide : HTTP_INTERCEPTORS,
        useClass : AuthInterceptor,
        multi : true
    }
];*/
