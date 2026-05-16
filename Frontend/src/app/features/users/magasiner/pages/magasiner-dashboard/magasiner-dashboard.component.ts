import { Component } from '@angular/core';
import { CreatePurchaseOrderComponent } from "../../../../PurchaseManagement/AdminPurchaseOrder/pages/create-purchase-order/create-purchase-order.component";
import { PurchaseOrderCreateComponent } from "../../../../PurchaseManagement/AdminPurchaseOrder/pages/purchase-order-create/purchase-order-create.component";

@Component({
  selector: 'app-magasiner-dashboard',
  standalone: true,
  imports: [ PurchaseOrderCreateComponent],
  templateUrl: './magasiner-dashboard.component.html',
  styleUrl: './magasiner-dashboard.component.css'
})
export class MagasinerDashboardComponent {

}
