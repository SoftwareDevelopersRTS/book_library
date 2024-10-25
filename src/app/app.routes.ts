import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { BooksComponent } from './books/books.component';
import { CategoryComponent } from './category/category.component';
import { LibraryComponent } from './library/library.component';
import { UserComponent } from './user/user.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ProfileComponent } from './profile/profile.component';
import { AddEditBookComponent } from './books/add-edit-book/add-edit-book.component';
import { NgModule } from '@angular/core';
import { AuthGuard } from './auth.guard';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'dashboard', component: DashboardComponent ,canActivate: [AuthGuard]},
    { path: 'login', component: LoginComponent },
    // { path: '**', component: LoginComponent },
    { path: 'books', component: BooksComponent ,canActivate: [AuthGuard]},
    { path: 'category', component: CategoryComponent,canActivate: [AuthGuard] },
    {
        path: 'library', component: LibraryComponent,canActivate: [AuthGuard]
    },
    {
        path: 'user', component: UserComponent,canActivate: [AuthGuard]
    }, {
        path: 'sidebar', component: SidebarComponent,canActivate: [AuthGuard]
    },
    {
        path: 'profile', component: ProfileComponent,canActivate: [AuthGuard]
    }, {
        path: 'add-edit-book', component: AddEditBookComponent,canActivate: [AuthGuard]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }
