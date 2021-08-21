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

  @Input() item:Item = {
    itemId: 0,
    itemName: '',
    itemPrice: 0,
    invQuantity: 0
  }

  constructor() { }

  ngOnInit(): void {
    
  }

}
