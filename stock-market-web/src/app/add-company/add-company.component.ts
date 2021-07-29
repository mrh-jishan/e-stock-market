import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.scss']
})
export class AddCompanyComponent implements OnInit {

  addCompanyForm: FormGroup;

  constructor(private _fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.addCompanyForm = this._fb.group({
      code: ['', Validators.required],
      name: ['', Validators.required],
      ceo: ['', Validators.required],
      turnover: ['', Validators.required],
      website: ['', Validators.required],
      exchangeCode: ['', Validators.required],

    })
  }

}
