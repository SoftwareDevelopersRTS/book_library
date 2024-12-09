import { Component, OnInit } from '@angular/core';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { SoundService } from '../sound.service';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [SidebarComponent],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.css'
})
export class SettingsComponent implements OnInit{
  ngOnInit(): void {
    
  }

  constructor(private sound:SoundService){

  }
  enableSound(type:string){
    this.sound.onOffSound(type);
  }
}
