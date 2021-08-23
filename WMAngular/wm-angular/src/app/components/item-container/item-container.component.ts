import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from 'src/app/Item';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-item-container',
  templateUrl: './item-container.component.html',
  styleUrls: ['./item-container.component.css']
})
export class ItemContainerComponent implements OnInit {

  orderHeaders= ["Item Id","Item Name","Price"]
  items: Observable<Item[]> = new Observable<Item[]>();

  constructor(private itemService:ItemService) { }

  ngOnInit(): void {
    this.items = this.itemService.subject;
    
  }

}
