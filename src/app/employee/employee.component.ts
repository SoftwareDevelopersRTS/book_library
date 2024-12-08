import { Component, OnInit } from '@angular/core';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { CommonService } from '../common.service';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [SidebarComponent],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css'
})
export class EmployeeComponent implements OnInit {

  employeeListPagination: any = {};
  constructor(private common: CommonService) {

  }
  ngOnInit(): void {
    this.getEmployeeList();
  }


  getEmployeeList() {
    this.common.postRequest(this.common.SERVER_URL['EMPLOYEE_LIST'],this.employeeListPagination).subscribe((response) => {
      if (response.status == 200) {

      }
    })
  }
}
