import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
@Component({
  selector: 'app-login-admin',
  standalone: true,
  imports: [MatIconModule,CommonModule],
  templateUrl: './login-admin.component.html',
  styleUrl: './login-admin.component.css'
})
export class LoginAdminComponent {
  isPasswordVisible: boolean = false;
  togglePassword() {
    this.isPasswordVisible = !this.isPasswordVisible;
  }
}
