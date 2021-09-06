import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject} from "rxjs";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private isLogged = new BehaviorSubject<boolean>(false);
  currentFlag = this.isLogged.asObservable();

  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  });

  constructor(private http: HttpClient) {
    this.isLogged.next(this.isAuthenticated())
  }

  changeFlag(flag: boolean) {
    this.isLogged.next(flag)
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  public getToken(): string {
    return localStorage.getItem('token');
  }

  public setToken(token) {
    localStorage.setItem('token', token);
  }

  clearToken() {
    localStorage.clear();
  }

  login(body) {
    return this.http.post(`${environment.apiUrl}/api/v1.0/auth/login`, body, {
      headers: this.headers,
      responseType: "json",
      observe: "response",
      withCredentials: true
    })
  }

  register(body) {
    return this.http.post(`${environment.apiUrl}/api/v1.0/auth/register`, body, {
      headers: this.headers,
      observe: "response",
      responseType: "json",
      withCredentials: true
    })
  }

  createCompany(body) {
    return this.http.post(`${environment.apiUrl}/api/v1.0/market/company/register`, body, {headers: this.headers})
  }

  addStock(companyCode, body) {
    return this.http.post(`${environment.apiUrl}/api/v1.0/market/stock/add/${companyCode}`, body, {headers: this.headers})
  }

  getCompanyList() {
    return this.http.get(`${environment.apiUrl}/api/v1.0/market/company/getall`, {headers: this.headers})
  }

  getStockList(companyCode) {
    return this.http.get(`${environment.apiUrl}/api/v1.0/market/company/info/${companyCode}`, {headers: this.headers})
  }

  getFilterStock(companyCode, startDate, endDate) {
    return this.http.get(`${environment.apiUrl}/api/v1.0/market/stock/get/${companyCode}/${startDate}/${endDate}`, {headers: this.headers})
  }
}
