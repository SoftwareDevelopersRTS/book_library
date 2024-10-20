import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FileutilService {

  constructor() { }

  convertFileToBase64(file: File): Promise<string> {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.onloadend = () => {
        resolve(reader.result as string);
      };
      reader.onerror = (error) => {
        reject(error);
      };
      reader.readAsDataURL(file);
    });
  }
  base64Provider(file: File): Promise<string | null> {
    if (file) {
      return this.convertFileToBase64(file)
        .then((base64String) => {
          return base64String;
        })
        .catch((error) => {
          console.error('Error converting file to Base64:', error);
          return null;
        });
    }
    return Promise.resolve(null); // Return null if no file is provided
  }

}
