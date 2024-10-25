import { Component, OnInit } from '@angular/core';
import { SidebarComponent } from "../sidebar/sidebar.component";
import { CommonService } from '../common.service';
import { STATUS_CODES } from '../app.constants';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { Router } from '@angular/router';
declare var $: any;

@Component({
  selector: 'app-books',
  standalone: true,
  imports: [SidebarComponent, CommonModule, FormsModule, NgxPaginationModule],
  templateUrl: './books.component.html',
  styleUrl: './books.component.css'
})
export class BooksComponent implements OnInit {
  bookListPagination: any = { pageNo: 1, numPerPage: 12 }
  bookCommentPagination: any = { pageNo: 1, numPerPage: 10 }
  books: any = [];
  comments: any = [];
  bookListCount: number = 0;
  commentCount: number = 0;
  currentBookId: any;
  constructor(private common: CommonService, private router: Router) {

  }
  ngOnInit(): void {
    this.getBooksList();
  }

  // ngAfterViewInit() {
  //   // Your jQuery code to open a popup
  //   $('#myModal').modal('show');
  // }

  getBooksList() {
    this.common.postRequest(this.common.SERVER_URL['BOOK_LIST'], this.bookListPagination).subscribe(
      (response: any) => {
        if (response.status == STATUS_CODES.SUCCESS) {
          this.books = response.result;
          this.bookListCount = response.listCount;
        }
      }
    )
  }
  changeBookStatus(bookId: number) {
    this.common.getRequest(this.common.SERVER_URL['CHANGE_BOOK_STATUS'] + bookId).subscribe(
      (response: any) => {
        if (response.status == STATUS_CODES.SUCCESS) {
          this.getBooksList()
        }
      }
    )

  }
  changePage(pageNo: any, type: string) {
    if (type == 'comment') {
      this.bookCommentPagination.pageNo = pageNo;
      this.getBookComments();
    } else {
      this.bookListPagination.pageNo = pageNo;
      this.getBooksList();
    }
  }

  getBookById(bookId: number) {
    this.common.getRequest(this.common.SERVER_URL['GET_BOOK_BY_ID'] + bookId).subscribe((response: any) => {

    })
  }

  editBook(book: any) {
    this.router.navigateByUrl('/add-edit-book', { state: { book } });
  }

  openClosePopups(popupName: string, actionType: string, forWhat: string, extraId: number) {
    $('#' + popupName).modal(actionType);
    if (actionType == 'show' && forWhat === 'comments') {
      this.comments = [];
      this.commentCount = 0;
      this.currentBookId = extraId
      this.getBookComments();
    }
  }

  getBookComments() {
    this.bookCommentPagination.id = this.currentBookId
    this.common.postRequest(this.common.SERVER_URL['GET_BOOK_COMMENTS'], this.bookCommentPagination).subscribe((response: any) => {
      if (response.status == 200) {
        this.comments = response.result;
        this.commentCount = response.listCount;
      }
    })
  }
  deleteComment(commentId: number) {
    this.common.deleteRequest(this.common.SERVER_URL['DELETE_COMMENT'] + commentId).subscribe((response: any) => {
      if (response.status = 200) {

        this.getBookComments()
      }
    })
  }
  goToAddEditBookPage() {
    this.router.navigate(['/add-edit-book']);
  }
}
