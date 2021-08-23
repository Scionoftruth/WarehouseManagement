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
  invQuantity:number=0;


  constructor(private http:HttpClient) { }

  getAllItems(){
      this.http.get<Item[]>("http://ec2-18-118-134-128.us-east-2.compute.amazonaws.com:8080/item/stock")
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
      return this.http.get<Item>(`http://ec2-18-118-134-128.us-east-2.compute.amazonaws.com:8080/item/stock/${itemId}`)
      .pipe(
        catchError((e)=>{
          return throwError(e);
        }));
  }

  

  addItem(itemId:number,itemName:string,itemPrice:number,itemQuantity:number):Observable<String>{
      return this.http.post<String>("http://ec2-18-118-134-128.us-east-2.compute.amazonaws.com:8080/item/stock/add", JSON.stringify({itemId,itemName,itemPrice,itemQuantity}),{
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
