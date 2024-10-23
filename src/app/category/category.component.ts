import { Component, OnInit } from '@angular/core';
import { CommonService } from '../common.service';
import { SidebarComponent } from "../sidebar/sidebar.component";
import { STATUS_CODES } from '../app.constants';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { FileutilService } from '../fileutil.service';
declare var $: any;
@Component({
  selector: 'app-category',
  standalone: true,
  imports: [SidebarComponent, CommonModule, FormsModule, NgxPaginationModule],
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent implements OnInit {

  bookCategoryPagination: any = { pageNo: 1, numPerPage: 10 }
  categoryList: any[] = [];
  categoryListCount: number = 0;
  category: any = {};
  imageDataBo: any = {};
  constructor(private common: CommonService, private fileUtil: FileutilService) {

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

  openCloseModal(type: string) {
    $('#myModal').modal(type);
  }


  async onUpload(event: Event, fileType: string) {
    const input = event.target as HTMLInputElement; // Cast the event target to HTMLInputElement

    if (input.files && input.files.length > 0) {
      const file = input.files[0]; // Get the first file

      try {
        // Use a switch statement for cleaner control flow over fileType
        switch (fileType) {
          case 'frontImage':
            this.imageDataBo.encodedFrontImage = await this.fileUtil.base64Provider(file);
            console.log('Frontside Image uploaded:', file);
            break;

          case 'backImage':
            this.imageDataBo.encodedBackImage = await this.fileUtil.base64Provider(file);
            console.log('Backside Image uploaded:', file);
            break;

          case 'bookFile':
            // Handle book file if necessary
            console.log('Book file uploaded:', file);
            break;

          default:
            console.error('Unknown fileType:', fileType);
            break;
        }

        // Log the uploaded image data
        console.log("Encoded Front Image:", this.imageDataBo.encodedFrontImage);
        console.log("Encoded Back Image:", this.imageDataBo.encodedBackImage);

      } catch (error) {
        console.error('Error during file upload:', error);
      }

    } else {
      console.error('No file selected');
    }
  }


  addEditCategory() {
    if (this.category.bookCategoryId && this.category.bookCategoryUniqueId) {

    } else {
      this.category.imageDataBo = this.imageDataBo;
      this.common.postRequest(this.common.SERVER_URL['ADD_BOOK_CATEGORY'], this.category).subscribe(
        (response) => {

        }
      );
    }

  }

  changeBookCategoryStatus(bookCategoryId: number) {
    this.common.getRequest(this.common.SERVER_URL['CHANGE_BOOK_CATEGORY_STATUS'] + bookCategoryId).subscribe(
      (response: any) => {
        if (response.status == STATUS_CODES.SUCCESS) {
          this.bookCategoryList()
        }
      }
    )

  }
  editBookCategory(bookCategory: any) {
    this.category = bookCategory;
    this.openCloseModal('show')
  }


  getCategoryById(bookCategoryId: number) {
    this.common.getRequest(this.common.SERVER_URL['GET_BOOK_CATEGORY_BY_ID']).subscribe((response: any) => {
      if (response.status == 200) {
        this.category = response.result;
      }
    })
  }


}
