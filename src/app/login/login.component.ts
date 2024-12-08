import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonService } from '../common.service';
import { STATUS_CODES } from '../app.constants';
import { NgSelectModule } from '@ng-select/ng-select';
import { DropdownModule } from 'primeng/dropdown';
import { SoundService } from '../sound.service';
import { AlertService } from '../alert.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, DropdownModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  allSecurityRoles: any[] = [];
  loginInfo: any = {};

  constructor(private common: CommonService, private soundService: SoundService, private alert: AlertService, private router: Router) {

  }
  ngOnInit(): void {
    this.getAllSecurityRoles();
  }

  getAllSecurityRoles() {
    this.common.getRequest(this.common.SERVER_URL['ALL_SECURITY_ROLES']).subscribe(
      (response: any) => {
        if (response.status == STATUS_CODES.SUCCESS) {
          this.allSecurityRoles = response.result;
        }
      }, (error) => {
        console.log(error)
      }
    );
  }

  sysytemUserLogin() {
    console.log(JSON.stringify(this.loginInfo))
    this.common.postRequest(this.common.SERVER_URL['SYSTEM_USER_LOGIN'], this.loginInfo).subscribe(
      (response: any) => {
        if (response.status == 200) {
          this.alert.showSuccessAlert('', response.message);
          this.soundService.playSound('LOGIN_SUCESS_SOUND');
          this.router.navigateByUrl('/dashboard')
        } else {
          this.alert.showFailureAlert("", response.message)
          this.soundService.playSound('LOGIN_FAILURE_SOUND')
        }
      }, (error) => {
        this.alert.showFailureAlert("", "Something Went wrong try again");
        this.soundService.playSound('LOGIN_FAILURE_SOUND')
      }
    );
  }

  passwordDataType: string = 'password';
  togglePassword() {
    this.passwordDataType == 'text' ? this.passwordDataType = 'password' : this.passwordDataType = 'text';
  }

}
