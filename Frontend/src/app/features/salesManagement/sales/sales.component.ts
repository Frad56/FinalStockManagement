import { Component, inject } from '@angular/core';
import { ProductVariantSearchByCodeComponent } from '../product-variant-search-by-code/product-variant-search-by-code.component';
import { CartComponent } from '../cart/cart.component';
import { CheckoutComponent } from '../checkout/checkout.component';
import { ProductVariant } from '../../../shared/models/StockManagment/ProductVariant.model';
import { CartItem } from '../../../shared/models/salesManagement/cartItem.model';
import { SalesOrderService } from '../../../core/services/salesManagement/salesOrder/sales-order.service';
import { SalesOrderLineDTO } from '../../../shared/models/dto/SalesManegementDTO/SalesOrderLine.dto';
import { SalesOrderDTO } from '../../../shared/models/dto/SalesManegementDTO/SalesOrder.dto';
import {  Location } from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-sales',
  standalone: true,
  imports: [ProductVariantSearchByCodeComponent,
  CartComponent,
CheckoutComponent],
  templateUrl: './sales.component.html',
  styleUrl: './sales.component.css'
})
export class SalesComponent {

  cartItems: CartItem[] = [];

  constructor(private salesOderService: SalesOrderService) {}

  
  addProduct(product: ProductVariant) {

    let existing = this.cartItems.find(
      p => p.productVariantId === product.productVariantId
    );
  
    // produit déjà dans panier
    if (existing) {
  
      // vérifier stock
      if (existing.quantity < existing.quantityInStock) {
  
        existing.quantity++;
  
      } else {
  
        Swal.fire({
          icon: 'warning',
          title: 'Insufficient Stock',
          text: 'Maximum stock quantity reached.',
          confirmButtonColor: '#f59e0b'
        });
        return;
      }
  
    } else {
  
      const stock = product.quantityInStock ?? 0;
      // vérifier si stock = 0
      if (stock <= 0) {
  
        Swal.fire({
          icon: 'warning',
          title: 'Insufficient Stock',
          text: 'Maximum stock quantity reached.',
          confirmButtonColor: '#f59e0b'
        });
        return;
      }
  
      this.cartItems.push({
        productVariantId: product.productVariantId!,
        code: product.code!,
        unitPrice: product.specificPrice!,
        quantity: 1,
        quantityInStock: product.quantityInStock!,
        discount: 0
      });
    }
  }

  updateCart(items: CartItem[]) {
    this.cartItems = items;
  }


  mapToSalesOrderLines(): SalesOrderLineDTO[] {
    return this.cartItems.map(item => ({

      productVariantId: item.productVariantId,
      quantity: item.quantity,
      unitPrice :item.unitPrice,
      discount: item.discount,
    }));
  }


  confirmOrder(data: any) {

    const order: SalesOrderDTO = {
      //clientId: 3,
      paymentType: data.paymentType,
      salesOrderLineListDTO: this.mapToSalesOrderLines()
    };
  
    console.log("ORDER SENT:", order);
  
    this.salesOderService.addSalesOrder(order).subscribe({
      next: () => {
        alert("Vente validée !");
        this.cartItems = [];
      },
      error: (err) => {
        console.error(err);
        console.log(err.error);
        console.log(err.status);
        console.log(err.message);
      }
    });
  }

  private location = inject(Location);
  goBack(){
    this.location.back();
  }
}
