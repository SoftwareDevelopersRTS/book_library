import { Component, OnInit } from '@angular/core';
import { CommonService } from '../common.service';
import { SidebarComponent } from "../sidebar/sidebar.component";
import { STATUS_CODES } from '../app.constants';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-category',
  standalone: true,
  imports: [SidebarComponent,CommonModule,FormsModule,NgxPaginationModule],
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent implements OnInit {

  bookCategoryPagination: any = { pageNo: 1, numPerPage: 10 }
  categoryList: any[] = [];
  categoryListCount: number = 0;
  constructor(private common: CommonService) {

  }
  ngOnInit(): void {
    this.bookCategoryList();
  }

  bookCategoryList() {
    this.common.postRequest(this.common.SERVER_URL['BOOK_CATEGORY_LIST'], this.bookCategoryPagination).subscribe(
      (response: any) => {
        if (response.status == STATUS_CODES.SUCCESS) {
          this.categoryList = response.result;
          this.categoryListCount = response.listCount;
        }
      }, (error) => {

      }
    )
  }

  changePage(pageNo: any) {
    this.bookCategoryPagination.pageNo = pageNo;
    this.bookCategoryList();
  }
}
