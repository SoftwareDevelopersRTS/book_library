import { Routes } from '@angular/router';
import { LoginRoutingModule } from './login/login-routing.module';

export const routes: Routes = [
  {
    path:'',
    loadChildren:()=>import('./login/login-routing.module').then(n=>n.LoginRoutingModule)
  }
];
