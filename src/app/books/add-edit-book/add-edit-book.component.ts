import { Component, OnInit } from '@angular/core';
import { SidebarComponent } from '../../sidebar/sidebar.component';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { trigger, transition, style, animate } from '@angular/animations';

import { FileUploadModule } from 'primeng/fileupload';
import { CommonService } from '../../common.service';
import { STATUS_CODES } from '../../app.constants';
import { MultiSelectModule } from 'primeng/multiselect';
import { FileutilService } from '../../fileutil.service';
// import { BrowserModule } from '@angular/platform-browser';
// import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
@Component({
  selector: 'app-add-edit-book',
  standalone: true,
  imports: [SidebarComponent, DropdownModule, FormsModule, CommonModule, FileUploadModule, MultiSelectModule],
  templateUrl: './add-edit-book.component.html',
  styleUrl: './add-edit-book.component.css',
  animations: [
    trigger('overlayContentAnimation', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('300ms', style({ opacity: 1 }))
      ]),
      transition(':leave', [
        animate('300ms', style({ opacity: 0 }))
      ])
    ])
  ]
})
export class AddEditBookComponent implements OnInit {

  libraries: any[] = [];
  book: any = {};
  categories: any[] = []
  imageDataBo: any = {};

  constructor(private common: CommonService, private fileUtil: FileutilService) {

  }
  ngOnInit(): void {
    this.getBookCategoryList();
    this.getLibraryList();
  }

  getBookCategoryList() {
    this.common.getRequest(this.common.SERVER_URL['ALL_BOOK_CATEGORY_LIST']).subscribe(
      (response: any) => {
        if (response.status == 200) {
          this.categories = response.result;
        }
      }
    )
  }

  getLibraryList() {
    this.common.getRequest(this.common.SERVER_URL['ALL_LIBRARY_LIST']).subscribe(
      (response: any) => {
        if (response.status == 200) {
          this.libraries = response.result;
        }
      }
    )
  }

  onSubmit() {
    this.book.imageDataBo = this.imageDataBo;
    this.common.postRequest(this.common.SERVER_URL['ADD_BOOK'], this.book).subscribe((response: any) => {

    })
  }

  async onUpload(event: Event, fileType: string) {
    const input = event.target as HTMLInputElement; // Cast the event target to HTMLInputElement
  
    if (input.files && input.files.length > 0) {
      const file = input.files[0]; // Get the first file
  
      try {
        // Call your base64Provider function
        if (fileType === 'frontImage') {
          this.imageDataBo.encodedFrontImage = await this.fileUtil.base64Provider(file);
          console.log('Frontside Image uploaded:', file);
        } else if (fileType === 'backImage') {
          this.imageDataBo.encodedBackImage = await this.fileUtil.base64Provider(file);
          console.log('Backside Image uploaded:', file);
        } else if (fileType === 'bookFile') {
          // Handle book file if necessary
        }
  
        console.log("this.book.imageDataBo.encodedFrontImage----------->" + this.imageDataBo.encodedFrontImage);
        console.log("this.book.imageDataBo.encodedBackImage----------->" + this.imageDataBo.encodedBackImage);
      } catch (error) {
        console.error('Error during file upload:', error);
      }
    } else {
      console.error('No file selected');
    }
  }


 
}
