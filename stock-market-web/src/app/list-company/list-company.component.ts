import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {AppService} from "../app.service";
import {Company} from "../add-stock/add-stock.component";

export interface ICompany {
  ceo: string,
  code: string,
  exchangeCode: string,
  name: string,
  turnover: number,
  website: string
}

@Component({
  selector: 'app-list-company',
  templateUrl: './list-company.component.html',
  styleUrls: ['./list-company.component.scss']
})
export class ListCompanyComponent implements OnInit {

  displayedColumns: string[] = ['ceo', 'code', 'exchangeCode', 'name', 'turnover', 'website'];
  companyList: ICompany[]

  constructor(private _fb: FormBuilder,
              private appService: AppService) {
  }

  ngOnInit(): void {
    this.appService.getCompanyList()
      .subscribe((res: Company[]) => {
        this.companyList = res;
      }, error => {
        console.log('err: ', error)
      })
  }

}
