import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AppService} from "../app.service";
import {MatDialog} from "@angular/material/dialog";
import {DialogComponent} from "../common/dialog/dialog.component";
import {Router} from "@angular/router";

export interface Company {
  "code": string,
  "name": string,
  "ceo": string,
  "turnover": number,
  "website": string,
  "exchangeCode": string

}

@Component({
  selector: 'app-add-stock',
  templateUrl: './add-stock.component.html',
  styleUrls: ['./add-stock.component.scss']
})
export class AddStockComponent implements OnInit {

  addStockForm: FormGroup;

  companyList: Company[]

  constructor(private _fb: FormBuilder,
              private appService: AppService,
              private dialog: MatDialog,
              private router: Router) {
  }

  ngOnInit(): void {
    this.addStockForm = this._fb.group({
      companyCode: ['', Validators.required],
      price: ['', Validators.compose([Validators.required, Validators.min(1), Validators.max(Infinity)])]
    })

    this.appService.getCompanyList()
      .subscribe((res: Company[]) => {
        this.companyList = res;
      }, error => {
        console.log('err: ', error)
      })
  }

  addStock() {
    const values = this.addStockForm.value;
    this.appService.addStock(values.companyCode, {price: values.price})
      .subscribe(res => {
        console.log(res)
        this.router.navigateByUrl('/dashboard');
      }, error => {
        console.log('err: ', error);
        this.dialog.open(DialogComponent, {
          width: '350px',
          data: {title: 'Error Message!', message: 'Sorry! Something wrong happen. Please try again...'}
        });
      })
  }
}
