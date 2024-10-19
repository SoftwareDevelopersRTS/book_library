import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonService } from '../common.service';
import {STATUS_CODES  } from '../app.constants';
import { NgSelectModule } from '@ng-select/ng-select';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  allSecurityRoles: any[] = [];
  loginInfo: any = {};

  constructor(private common: CommonService) {

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
      (response) => {

      }
    );
  }

}
