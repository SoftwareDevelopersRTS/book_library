import { Component, OnInit } from '@angular/core';
import { CommonService } from '../../../common.service';

@Component({
  selector: 'app-add-book',
  standalone: true,
  imports: [],
  templateUrl: './add-book.component.html',
  styleUrl: './add-book.component.css'
})
export class AddBookComponent implements OnInit {


  constructor(private common: CommonService) {

  }
  ngOnInit(): void {
   
  }
}
