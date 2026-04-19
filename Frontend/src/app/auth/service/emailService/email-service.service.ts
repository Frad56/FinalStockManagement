import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { EmailRequestDTO } from '../../authDTO/EmailRequestDTO';

@Injectable({
  providedIn: 'root'
})
export class EmailServiceService {

  private readonly API = 'http://localhost:8080/api/email';
  private http = inject(HttpClient);


  sendCode(email:EmailRequestDTO){
    return this.http.post<{message:string}>(`${this.API}/send-email`, email);
  }
  

}
