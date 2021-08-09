import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AppService} from "../app.service";
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {DialogComponent} from "../common/dialog/dialog.component";
import {HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private _fb: FormBuilder,
              private appService: AppService,
              private dialog: MatDialog,
              private router: Router) {
  }

  ngOnInit(): void {
    this.loginForm = this._fb.group({
      username: ['robin1234', Validators.compose([Validators.required, Validators.minLength(6)])],
      password: ['hithere', Validators.compose([Validators.required, Validators.minLength(6)])]
    })
  }

  loginSubmit() {
    this.appService.login(this.loginForm.value)
      .subscribe((res: HttpResponse<any>) => {
        console.log('res: ', res)
        console.log('header: ', res.headers.get("Authorization"))
        // this.router.navigateByUrl('/dashboard');
      }, error => {
        console.log('err: ', error)
        this.dialog.open(DialogComponent, {
          width: '350px',
          data: {title: 'Error Message!', message: 'Sorry! Something wrong happen. Please try again...'}
        });
      })
  }
}
