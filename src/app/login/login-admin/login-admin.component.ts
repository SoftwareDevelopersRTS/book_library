import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { NgSelectModule } from '@ng-select/ng-select';
@Component({
  selector: 'app-login-admin',
  standalone: true,
  imports: [MatIconModule,CommonModule,NgSelectModule,FormsModule],
  templateUrl: './login-admin.component.html',
  styleUrl: './login-admin.component.css'
})
export class LoginAdminComponent {
  isPasswordVisible: boolean = false;
  adminData:any={};
  LibraryRoles = [
    { id: 1, name: 'Admin' },
    { id: 2, name: 'Librarian' },
    { id: 3, name: 'Book Inventory Manager' },
  ];
  togglePassword() {
    this.isPasswordVisible = !this.isPasswordVisible;
  }

}
