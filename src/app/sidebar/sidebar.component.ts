import { Component, HostListener, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { RouterModule } from '@angular/router';
import { SoundService } from '../sound.service';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterLink, RouterModule,CommonModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent implements OnInit {

  tab: any;
  isPlaySound!: string;
  @HostListener('document:click', ['$event.target'])
  handleSidebarClick(target: HTMLElement): void {
    if (target.tagName === 'A' || target.closest('a')) {
      this.sound.playSound('CLICK_SOUND');
    }
  }

  constructor(private router: Router, private sound: SoundService) {
    const isPlaySound = localStorage.getItem('isPlaySound')
    this.isPlaySound = isPlaySound ? isPlaySound : 'false';
    this.tab = 'dashboard';
    this.logCurrentRoute();
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        console.log('Navigation ended:', event);
      }
    });
  }
  ngOnInit(): void {

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

  logOut() {
    this.router.navigateByUrl('login')
  }
  onOffSound() {
    if(this.isPlaySound && this.isPlaySound=='true'){
      this.sound.onOffSound('on')
    }else{
      this.sound.onOffSound('off')
    }
   
  }
}
