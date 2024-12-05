import { Component } from '@angular/core';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterLink,RouterModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {

  tab: any;
  constructor(private router: Router) {
    this.tab = 'dashboard';
    this.logCurrentRoute();
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        console.log('Navigation ended:', event);
      }
    });
  }
  logCurrentRoute() {
    console.log(this.router.url);
  }

  isActiveTab(tab: string): boolean {
    return this.tab === tab; // Check if this tab is active
  }

  setActiveTab(tab: string): void {
    this.tab = tab; // Update the active tab
  }
}
