import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {DashboardComponent} from "./dashboard/dashboard.component";
import {AddCompanyComponent} from "./add-company/add-company.component";
import {AddStockComponent} from "./add-stock/add-stock.component";
import {ListCompanyComponent} from "./list-company/list-company.component";


const routes: Routes = [
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
    redirectTo: '/dashboard'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
