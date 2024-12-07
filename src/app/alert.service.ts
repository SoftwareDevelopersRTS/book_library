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
      ...options 
    });
  }


  showSuccessAlert(title: string, text: string): void {
    this.showAlert(title, text, 'success');
  }
  showFailureAlert(title: string, text: string): void {
    this.showAlert(title, text, 'error');
  }
  showWarningAlert(title: string, message: string): void {
    this.showAlert(title, message, 'warning');
  }
}
