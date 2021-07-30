import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AppService} from "../app.service";
import {MatDialog} from "@angular/material/dialog";
import {DialogComponent} from "../common/dialog/dialog.component";

export interface StockData {
  code: string,
  createdAt: string,
  id: string,
  price: number,
  updatedAt: string
}

export interface CompanyData {
  code: string,
  name: string,
  min: number,
  max: number,
  avg: number,
  stocks: StockData[]
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  companySearchForm: FormGroup;
  stockSearchForm: FormGroup;
  displayedColumns: string[] = ['id', 'code', 'price', 'createdAt', 'updatedAt'];
  companyData: CompanyData = {name: '', code: '', stocks: [], min: 0, max: 0, avg: 0};

  constructor(private _fb: FormBuilder,
              private appService: AppService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.companySearchForm = this._fb.group({
      companyCode: ['', Validators.required]
    })

    this.stockSearchForm = this._fb.group({
      fromDate: ['', Validators.required],
      toDate: ['', Validators.required]
    })
  }

  onSearchCompany() {
    this.appService.getStockList(this.companySearchForm.value.companyCode)
      .subscribe((res: CompanyData) => {
        console.log(res)
        this.companyData = res;
      }, error => {
        console.log('err: ', error)
        this.dialog.open(DialogComponent, {
          width: '350px',
          data: {title: 'Error Message!', message: 'Sorry! Not found. Please try again...'}
        });
      })
  }

}
