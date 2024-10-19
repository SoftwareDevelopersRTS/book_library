import { Component, OnInit } from '@angular/core';
import { CommonService } from '../common.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SidebarComponent } from '../sidebar/sidebar.component';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule,SidebarComponent],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
  userInfo: any = { address: {} };
  constructor(private common: CommonService) {

  }
  ngOnInit(): void {
    this.getUserDetailsById(1);
  }

  getUserDetailsById(id: number) {
    this.common.getRequest(this.common.SERVER_URL['GET_USER_DETAILS_BY_ID'] + id).subscribe((response: any) => {
      this.userInfo = response.result;
      JSON.stringify(this.userInfo)
    })
  }

  updateUserProfile() {
    this.common.putRequest(this.common.SERVER_URL['EDIT_PROFILE'], this.userInfo).subscribe()
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        // Assign full base64 string (including prefix) to userInfo.profileImage
        this.userInfo.profileImage = e.target.result;
        
        // Optionally, update profileImageUrl for real-time preview
        this.userInfo.profileImageUrl = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
  

  getProfileImageUrl(): string {
    if (this.userInfo && this.userInfo.profileImageUrl) {
      return `assets/${this.userInfo.profileImageUrl}`;
    } else {
      return 'assets/profile-placeholder.jpg';
    }
  }
}
