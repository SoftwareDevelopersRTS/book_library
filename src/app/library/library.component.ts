import { Component, OnInit } from '@angular/core';
import { STATUS_CODES } from '../app.constants';
import { CommonService } from '../common.service';
import { CommonModule } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { SidebarComponent } from '../sidebar/sidebar.component';

@Component({
  selector: 'app-library',
  standalone: true,
  imports: [SidebarComponent, CommonModule, NgxPaginationModule],
  templateUrl: './library.component.html',
  styleUrl: './library.component.css'
})
export class LibraryComponent implements OnInit {

  libraryPagination: any = { pageNo: 1, numPerPage: 10 }
  libraryList: any[] = [];
  libraryListCount: number = 0;
  constructor(private common: CommonService) {

  }
  ngOnInit(): void {
    this.getLibraryList();
  }

  getLibraryList() {
    this.common.postRequest(this.common.SERVER_URL['LIBRARY_LIST'], this.libraryPagination).subscribe(
      (response: any) => {
        if (response.status == STATUS_CODES.SUCCESS) {
          this.libraryList = response.result;
          this.libraryListCount = response.listCount;
        }
      }, (error) => {

      }
    )
  }

  changePage(pageNo: any) {
    this.libraryPagination.pageNo = pageNo;
    this.getLibraryList();
  }

  convertToDate(timeString: string): Date {
    const date = new Date();
    const [hours, minutes] = timeString.split(':').map(Number);
    date.setHours(hours, minutes, 0, 0); // Set hours and minutes
    return date; // Return the Date object
  }
}
