import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) {
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
}
