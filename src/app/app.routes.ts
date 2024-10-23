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

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'login', component: LoginComponent },
    // { path: '**', component: LoginComponent },
    { path: 'books', component: BooksComponent },
    { path: 'category', component: CategoryComponent },
    {
        path: 'library', component: LibraryComponent
    },
    {
        path: 'user', component: UserComponent
    }, {
        path: 'sidebar', component: SidebarComponent
    },
    {
        path: 'profile', component: ProfileComponent
    }, {
        path: 'add-edit-book', component: AddEditBookComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }