import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  });

  constructor(private http: HttpClient) {
  }

  public getToken(): string {
    return localStorage.getItem('token');
  }

  public isAuthenticated(): boolean {
    // get the token
    const token = this.getToken();
    // return a boolean reflecting
    // whether or not the token is expired
    // return tokenNotExpired(null, token);
    return false;
  }

  logout() {

  }

  login(body) {
    return this.http.post(`http://localhost:8080/api/v1.0/auth/login`, body, {
      headers: this.headers,
      responseType: "json",
      observe: "response",
      withCredentials: true
    })
  }

  register(body) {
    return this.http.post('http://localhost:8080/api/v1.0/auth/register', body, {
      headers: this.headers,
      observe: "response",
      responseType: "json",
      withCredentials: true
    })
  }

  createCompany(body) {
    return this.http.post('http://localhost:8080/api/v1.0/market/company/register', body, {headers: this.headers})
  }

  addStock(companyCode, body) {
    return this.http.post(`http://localhost:8080/api/v1.0/market/stock/add/${companyCode}`, body, {headers: this.headers})
  }

  getCompanyList() {
    return this.http.get('http://localhost:8080/api/v1.0/market/company/getall', {headers: this.headers})
  }

  getStockList(companyCode) {
    return this.http.get(`http://localhost:8080/api/v1.0/market/company/info/${companyCode}`, {headers: this.headers})
  }

  getFilterStock(companyCode, startDate, endDate) {
    return this.http.get(`http://localhost:8080/api/v1.0/market/stock/get/${companyCode}/${startDate}/${endDate}`, {headers: this.headers})
  }

}
