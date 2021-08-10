import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AppService} from "../app.service";
import {Router, Routes} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {DialogComponent} from "../common/dialog/dialog.component";

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.scss']
})
export class AddCompanyComponent implements OnInit {

  addCompanyForm: FormGroup;
  urlRegex = /^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&'\(\)\*\+,;=.]+$/;


  constructor(private _fb: FormBuilder,
              private appService: AppService,
              private router: Router,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.addCompanyForm = this._fb.group({
      code: ['', Validators.required],
      name: ['', Validators.required],
      ceo: ['', Validators.required],
      turnover: ['', Validators.required, Validators.min(100000000)],
      website: ['', Validators.compose([Validators.required, Validators.pattern(this.urlRegex)])],
      exchangeCode: ['', Validators.required],

    })
  }

  addCompany() {
    this.appService.createCompany(this.addCompanyForm.value).subscribe(res => {
      console.log('res: ', res)
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
