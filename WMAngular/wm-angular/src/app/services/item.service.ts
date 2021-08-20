import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError, Subject } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Item } from '../Item';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  itemId:number=0;
  itemName:string='';
  itemPrice:number=0;
  itemQuantity:number=0;
  items: Item[] = [];
  subject: Subject<Item[]> = new Subject<Item[]>();


  constructor(private http:HttpClient) { }

  getAllItems(){
      this.http.get<Item[]>("http://localhost:8080/stock")
      .pipe(
        catchError((e)=>{
          return throwError(e);
        }))
        .subscribe(
          (data) => {
            this.items = data;
            this.subject.next(this.items);
          }
        )
  }

  getItemById(itemId:number):Observable<Item>{
      return this.http.get<Item>(`http://localhost:8080/stock/${itemId}`)
      .pipe(
        catchError((e)=>{
          return throwError(e);
        }));
  }

  

  addItem(itemId:number,itemName:string,itemPrice:number,itemQuantity:number):Observable<String>{
      return this.http.post<String>("http://localhost:8080/stock/add", JSON.stringify({itemId,itemName,itemPrice,itemQuantity}),{
        headers:{
          "Content-Type":"application/json"
        }
      })
      .pipe(catchError((e)=>{
        return throwError(e);
      }));
  }

  

  /*

  */


}
