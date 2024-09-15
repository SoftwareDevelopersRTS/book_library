import { Routes } from '@angular/router';
import { LoginRoutingModule } from './login/login-routing.module';

export const routes: Routes = [
  {
    path:'login',
    loadChildren:()=>import('./login/login-routing.module').then(n=>n.LoginRoutingModule)
  },
  {
    path:'',
    loadChildren:()=>import('./dashboard/dashboard.module').then(n=>n.DashboardModule)
  }
];
