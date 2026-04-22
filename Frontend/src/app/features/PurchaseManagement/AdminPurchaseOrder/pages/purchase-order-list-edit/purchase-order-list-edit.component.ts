import { Component, inject } from '@angular/core';
import { PurchaseOrderService } from '../../../../../core/services/PurchaseManagement/PurchaseOrder/purchase-order.service';
import { CommonModule,Location } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import Swal from 'sweetalert2';
import { PurchaseOrder } from '../../../../../shared/models/PurchaseManagement/PurchaseOrder.model';
import { PurchaseOrderLineService } from '../../../../../core/services/PurchaseManagement/PurchaseOrderLine/purchase-order-line.service';

@Component({
  selector: 'app-purchase-order-list-edit',
  standalone: true,
  imports: [CommonModule
    ,MatTableModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule],
  templateUrl: './purchase-order-list-edit.component.html',
  styleUrl: './purchase-order-list-edit.component.css'
})
export class PurchaseOrderListEditComponent {

  private location = inject(Location);
  private purchaseOrderService = inject(PurchaseOrderService);
  private purchaseOrderLineService = inject(PurchaseOrderLineService);
  protected purchaseOrders:PurchaseOrder[] =[];
  displayedColumns: string[] = ['companyName', 'contactName', 'orderDate', 'status','totalAmount','actions'];

  loadPurchaseOrders(){
    this.purchaseOrderService.getPurchaseOrderListNotDelivered().subscribe({
      next:(response)=>{
        console.log("Purchase Orders:", response);
        this.purchaseOrders = response;
      },
      error:(error)=>{
        console.error("Error fetching purchase orders:", error);
      }
    }) ;
  }

  deletePurchaseOrder(id:number){
    Swal.fire({
      title: "Are you sure you want to delete this Purchase Order ?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
    }).then((result) => {
      if(result.isConfirmed){
        this.purchaseOrderLineService.deletePurchaseOrderLineByPurchaseOrderId(id).subscribe({
          next:(response)=>{
            console.log("purchase Order Line ListDeleted! :",response)
            this.purchaseOrderService.deletePurchaseOrder(id).subscribe({ 
              next:(response)=>{
                console.log("Deleted! :",response)
                Swal.fire('Deleted!', 'The product Variant has been deleted.', 'success');
                this.loadPurchaseOrders();
              },error:(error)=>{
                Swal.fire({
                  icon: 'error',
                  title: 'Error',
                  text: error.error?.message || 'An error occurred while deleting!'
                });
              }
            });
          },error:(error)=>{
            Swal.fire({
              icon: 'error',
              title: 'Error',
              text: error.error?.message || 'An error occurred while deleting purchaseOrderLine!'
            });
          }
        });
        
      }
    }) 
    }






  goBack(){
    this.location.back();
  }
}
