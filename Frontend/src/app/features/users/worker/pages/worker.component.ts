import { Component } from '@angular/core';
import { AddItemToSaleOrderComponent } from "../../../salesManagement/add-item-to-sale-order/add-item-to-sale-order.component";
import { SalesComponent } from "../../../salesManagement/sales/sales.component";

@Component({
  selector: 'app-worker',
  standalone: true,
  imports: [SalesComponent],
  templateUrl: './worker.component.html',
  styleUrl: './worker.component.css'
})
export class WorkerComponent {

}
