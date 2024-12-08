import { Component, OnInit } from '@angular/core';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { CommonService } from '../common.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [SidebarComponent, FormsModule, CommonModule, NgxPaginationModule],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css'
})
export class EmployeeComponent implements OnInit {

  employeeListPagination: any = { pageNo: 1, numPerPage: 10 };
  employeeList: any = [];
  employeeCount: number = 0;
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
}
