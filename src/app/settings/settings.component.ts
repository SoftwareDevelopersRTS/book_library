import { Component, OnInit } from '@angular/core';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { SoundService } from '../sound.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CommonService } from '../common.service';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [SidebarComponent, FormsModule, CommonModule],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.css'
})
export class SettingsComponent implements OnInit {

  selectedTab: string;
  commonAppSettingList: any = [];
  commonAppSettingCount!: number
  roles: any = [];
  commonAppSettingListPagination: any = { pageNo: 1, numPerPage: 10 };
  ngOnInit(): void {
    this.selectTab(this.selectedTab)
  }

  constructor(private sound: SoundService, private common: CommonService) {
    this.selectedTab = 'common_app_setting';

  }
  enableSound(type: string) {
    this.sound.onOffSound(type);
  }
  selectTab(tab: string): void {
    console.log('Selected Tab:', tab);
    this.selectedTab = tab;
    if (this.selectedTab == 'common_app_setting') {
      this.getCommonAppSettingList();
    } else if (this.selectedTab == 'user_roles') {
      this.getAllSecurityRoles();
    }
  }

  getCommonAppSettingList() {
    this.common.postRequest(this.common.SERVER_URL['COMMON_APP_SETTING_LIST'], this.commonAppSettingListPagination).subscribe((response) => {
      if (response.status == 200) {
        this.commonAppSettingList = response.result;
        this.commonAppSettingCount = response.listCount;
      } else {

      }
    })
  }

  getAllSecurityRoles() {
    this.common.getRequest(this.common.SERVER_URL['ALL_SECURITY_ROLES']).subscribe(
      (response: any) => {
        if (response.status == 200) {
          this.roles = response.result;
        }
      }, (error) => {
        console.log(error)
      }
    );
  }


}
