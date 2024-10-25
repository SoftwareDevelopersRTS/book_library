// auth.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    // Check if sessionStorage contains userInfo
    const userInfo = sessionStorage.getItem('userInfo');
    
    if (userInfo) {
      // If userInfo exists, allow access
      return true;
    } else {
      // If no userInfo, redirect to login page
      this.router.navigate(['/login']);
      return false;
    }
  }
}
