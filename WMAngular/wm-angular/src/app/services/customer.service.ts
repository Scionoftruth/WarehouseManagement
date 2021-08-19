import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { throwError,Observable } from 'rxjs';
import { catchError, retry} from 'rxjs/operators';
import {Customer} from '../Customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {



  customer:Customer = {
    custId: 0,
    email: ''
  }

  login(email:string, password:string):Observable<Customer>{
    return this.http.post<Customer>("http://localhost:8080/customer/login", JSON.stringify({email, password}))
      .pipe(catchError((e)=>{
        return throwError(e);
      }));
  }

  constructor(private http: HttpClient) { }
}
