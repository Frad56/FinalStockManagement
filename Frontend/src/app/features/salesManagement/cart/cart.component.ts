import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { ProductVariant } from '../../../shared/models/StockManagment/ProductVariant.model';
import { CommonModule } from '@angular/common';
import { CartItem } from '../../../shared/models/salesManagement/cartItem.model';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {

  @Input() items: CartItem[] = [];
  @Output() cartUpdated = new EventEmitter<CartItem[]>();

  notify() {
    this.cartUpdated.emit(this.items);
  }

  increaseQuantity(item: CartItem) {
    if (item.quantity < item.quantityInStock) {
      item.quantity++;
      this.notify();
    } else {
      Swal.fire({
        icon: 'warning',
        title: 'Stock limité',
        text: 'Maximum stock reached',
        confirmButtonColor: '#1e88e5'
      });
    }
  }

  decreaseQuantity(item: CartItem) {
    if (item.quantity > 1) {
      item.quantity--;
      this.notify();
    }
  }

  removeProduct(item: CartItem) {
    this.items = this.items.filter(p => p.productVariantId !== item.productVariantId);
    this.notify();
  }
}
