import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  success(message: string) {
    alert(message);
  }

  error(message: string) {
    alert(message);
  }
}