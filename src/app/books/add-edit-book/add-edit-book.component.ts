import { Component } from '@angular/core';
import { SidebarComponent } from '../../sidebar/sidebar.component';

@Component({
  selector: 'app-add-edit-book',
  standalone: true,
  imports: [SidebarComponent],
  templateUrl: './add-edit-book.component.html',
  styleUrl: './add-edit-book.component.css'
})
export class AddEditBookComponent {

}
