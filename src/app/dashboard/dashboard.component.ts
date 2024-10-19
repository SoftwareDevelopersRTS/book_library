import { Component, CUSTOM_ELEMENTS_SCHEMA, OnInit } from '@angular/core';
import { SidebarComponent } from "../sidebar/sidebar.component";
import { CommonService } from '../common.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [SidebarComponent,CommonModule,FormsModule,NgxPaginationModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  allCountsBO: any = {};
  pagination: any = {}
  itemPagination:any={pageNum:1,numPerPage:5}
  items: any[] = [ // Sample data for the table
    { id: 1, productName: 'Product A', quantity: 10, status: 'Received' },
    { id: 2, productName: 'Product B', quantity: 5, status: 'Pending' },
    { id: 3, productName: 'Product C', quantity: 20, status: 'Received' },
    { id: 4, productName: 'Product D', quantity: 15, status: 'Pending' },
    { id: 5, productName: 'Product E', quantity: 25, status: 'Received' },
    { id: 6, productName: 'Product F', quantity: 8, status: 'Pending' },
    { id: 7, productName: 'Product G', quantity: 30, status: 'Received' },
    { id: 8, productName: 'Product H', quantity: 12, status: 'Pending' },
    { id: 9, productName: 'Product I', quantity: 18, status: 'Received' },
    { id: 10, productName: 'Product J', quantity: 22, status: 'Pending' },
    { id: 11, productName: 'Product K', quantity: 16, status: 'Received' },
    { id: 12, productName: 'Product L', quantity: 14, status: 'Pending' },
    { id: 13, productName: 'Product M', quantity: 27, status: 'Received' },
    { id: 14, productName: 'Product N', quantity: 6, status: 'Pending' },
    { id: 15, productName: 'Product O', quantity: 11, status: 'Received' },
    // Add more items as needed
  ];
  constructor(private common: CommonService) {

  }
  ngOnInit(): void {
    this.getDashboardAllCounts();
  }
  getDashboardAllCounts() {
    this.common.postRequest(this.common.SERVER_URL['DASHBOARD_ALL_COUNTS'], this.pagination).subscribe(
      (response: any) => {
        this.allCountsBO = response.result;
      }
    )
  }
  changePage(pageNum:number){

  }

}
