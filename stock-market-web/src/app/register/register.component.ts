import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AppService} from "../app.service";
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {DialogComponent} from "../common/dialog/dialog.component";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;

  constructor(private _fb: FormBuilder,
              private appService: AppService,
              private dialog: MatDialog,
              private router: Router) {
  }

  ngOnInit(): void {
    this.registerForm = this._fb.group({
      fullName: ['robin-hassan', Validators.compose([Validators.required, Validators.minLength(6)])],
      username: ['rbn-'+new Date().getTime(), Validators.compose([Validators.required, Validators.minLength(6)])],
      password: ['password123', Validators.compose([Validators.required, Validators.minLength(6)])],
      rePassword: ['password123', Validators.compose([Validators.required, Validators.minLength(6)])],
      email: ['password123@gmail.com', Validators.compose([Validators.required, Validators.email, Validators.minLength(6)])]
    })
  }

  registerSubmit() {
    this.appService.register(this.registerForm.value).subscribe(res => {
      console.log('res: ', res)
      this.router.navigateByUrl('/login');
    }, error => {
      console.log('err: ', error)
      this.dialog.open(DialogComponent, {
        width: '350px',
        data: {title: 'Error Message!', message: 'Sorry! Something wrong happen. Please try again...'}
      });
    })
  }
}
