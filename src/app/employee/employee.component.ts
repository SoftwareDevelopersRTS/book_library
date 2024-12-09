import { Component, OnInit } from '@angular/core';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { CommonService } from '../common.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { DropdownModule } from 'primeng/dropdown';
declare var $: any;
@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [SidebarComponent, FormsModule, CommonModule, NgxPaginationModule,DropdownModule],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css'
})
export class EmployeeComponent implements OnInit {

  employeeListPagination: any = { pageNo: 1, numPerPage: 10 };
  employeeList: any = [];
  employeeCount: number = 0;
  employee: any = {};
  allSecurityRoles: any = [];
  constructor(private common: CommonService) {

  }
  ngOnInit(): void {
    this.getEmployeeList();
  }


  getEmployeeList() {
    this.common.postRequest(this.common.SERVER_URL['EMPLOYEE_LIST'], this.employeeListPagination).subscribe((response) => {
      if (response.status == 200) {
        this.employeeList = response.result;
        this.employeeCount = response.listCount;
      } else {

      }
    })
  }

  changePage(pageNo: any) {
    this.employeeListPagination.pageNo = pageNo;
    this.getEmployeeList();
  }
  toggleEmployeeForm(type: string) {
    if (type == 'show') {
      this.getAllSecurityRoles();
    }
    $('#employeeForm').modal(type);
  }

  getAllSecurityRoles() {
    this.common.getRequest(this.common.SERVER_URL['ALL_SECURITY_ROLES']).subscribe(
      (response: any) => {
        if (response.status == 200) {
          this.allSecurityRoles = response.result;
        }
      }, (error) => {
        console.log(error)
      }
    );
  }

  addEmployee() {
    this.common.postRequest(this.common.SERVER_URL['ADD_EMPLOYEE'], this.employee).subscribe(
      (response) => {

      }
    )
  }
}
