import { Component } from '@angular/core';
import { ProductVariantSearchByCodeComponent } from '../product-variant-search-by-code/product-variant-search-by-code.component';
import { CartItem } from '../../../shared/models/salesManagement/cartItem.model';
import { ProductVariant } from '../../../shared/models/StockManagment/ProductVariant.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-item-to-sale-order',
  standalone: true,
  imports: [ProductVariantSearchByCodeComponent],
  templateUrl: './add-item-to-sale-order.component.html',
  styleUrl: './add-item-to-sale-order.component.css'
})
export class AddItemToSaleOrderComponent {

  cartItems: CartItem[] = [];

  addProduct(product: ProductVariant) {

    let existing = this.cartItems.find(
      p => p.productVariantId === product.productVariantId
    );

    if (existing) {
      existing.quantity++;
    } else {
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


  increaseQuantity(item: CartItem) {
    if (item.quantity < item.quantityInStock) {
      item.quantity++;
    
    } else {
      Swal.fire({
        icon: 'warning',
        title: 'Stock limité',
        text: 'Maximum stock reached',
        confirmButtonColor: '#1e88e5'
      });
    }
  }
}
