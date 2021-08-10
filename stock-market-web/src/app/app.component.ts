import {Component, OnInit} from '@angular/core';
import {AppService} from "./app.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'stock-market-web';

  isLogged = false;

  constructor(private appService: AppService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.appService.currentFlag.subscribe(flag => {
      this.isLogged = flag;
    });
  }

  logout() {
    this.appService.clearToken();
    this.router.navigateByUrl('/login').then(() => this.isLogged = false);
  }
}
