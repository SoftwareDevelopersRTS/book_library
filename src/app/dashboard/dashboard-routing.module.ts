import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainDashBoardComponent } from './main-dash-board/main-dash-board.component';

const routes: Routes = [
  {
    path:'',
    component:MainDashBoardComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
