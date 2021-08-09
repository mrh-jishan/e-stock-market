import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {DashboardComponent} from "./dashboard/dashboard.component";
import {AddCompanyComponent} from "./add-company/add-company.component";
import {AddStockComponent} from "./add-stock/add-stock.component";
import {ListCompanyComponent} from "./list-company/list-company.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";


const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'add-company',
    component: AddCompanyComponent
  },
  {
    path: 'add-stock',
    component: AddStockComponent
  },
  {
    path: 'list-company',
    component: ListCompanyComponent
  },
  {
    path: '**',
    redirectTo: '/login'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
