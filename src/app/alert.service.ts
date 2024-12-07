import { Injectable } from '@angular/core';
import Swal, { SweetAlertIcon, SweetAlertOptions } from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor() { }


  private showAlert(
    title: string,
    text: string,
    icon: SweetAlertIcon,
    confirmButtonText: string = 'OK',
    options: SweetAlertOptions = {}
  ): void {
    Swal.fire({
      title,
      text,
      icon,
      confirmButtonText,
      ...options // Merge additional options
    });
  }

  /**
   * Shows a success alert with the provided title and text.
   * @param title - Title of the success alert
   * @param text - Text content of the success alert
   */
  showSuccessAlert(title: string, text: string): void {
    this.showAlert(title, text, 'success');
  }

  /**
   * Shows a failure alert with the provided title and text.
   * @param title - Title of the failure alert
   * @param text - Text content of the failure alert
   */
  showFailureAlert(title: string, text: string): void {
    this.showAlert(title, text, 'error');
  }
}
