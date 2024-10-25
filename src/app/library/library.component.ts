import { Component, OnInit } from '@angular/core';
import { STATUS_CODES } from '../app.constants';
import { CommonService } from '../common.service';
import { CommonModule } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { FormsModule } from '@angular/forms';
declare var $: any;
@Component({
  selector: 'app-library',
  standalone: true,
  imports: [SidebarComponent, CommonModule, NgxPaginationModule, FormsModule],
  templateUrl: './library.component.html',
  styleUrl: './library.component.css'
})
export class LibraryComponent implements OnInit {

  libraryPagination: any = { pageNo: 1, numPerPage: 10 }
  libraryList: any[] = [];
  libraryListCount: number = 0;
  library: any = {};
  address: any = {};
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

  openCloseModal(type: string) {
    $('#myModal').modal(type);
  }
  changePage(pageNo: any) {
    this.libraryPagination.pageNo = pageNo;
    this.getLibraryList();
  }

  addLibrary() {
    this.library.address = this.address;
    this.common.postRequest(this.common.SERVER_URL['ADD_LIBRARY'], this.library).subscribe(
      (response) => {

      }
    );

  }


  changeLibraryStatus(bookId: number) {
    this.common.getRequest(this.common.SERVER_URL['CHANGE_LIBRARY_STATUS'] + bookId).subscribe(
      (response: any) => {
        if (response.status == STATUS_CODES.SUCCESS) {
          this.getLibraryList()
        }
      }
    )

  }
}
