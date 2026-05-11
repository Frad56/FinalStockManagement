import { Component, OnInit, inject } from '@angular/core';
import { ProductService } from '../../../../../core/services/stockManagement/productService/product.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule, Location } from '@angular/common';
import { Observable } from 'rxjs';
import { ProductVariant } from '../../../../../shared/models/StockManagment/ProductVariant.model';
import { ProductVariantService } from '../../../../../core/services/stockManagement/productVariantService/product-variant.service';
import Swal from 'sweetalert2';
import { CharacteristicValue } from '../../../../../shared/models/StockManagment/CharacteristicValue.model';
import { CharacteristicValueService } from '../../../../../core/services/stockManagement/CharacteristicValueService/characteristic-value.service';

@Component({
  selector: 'app-product-variant-list',
  standalone: true,
  imports: [CommonModule
    ,MatTableModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule],
  templateUrl: './product-variant-list.component.html',
  styleUrl: './product-variant-list.component.css'
})
export class ProductVariantListComponent  implements OnInit{
private location = inject(Location);
private router = inject(Router);
private productVariantService = inject(ProductVariantService);
private characteristicValueService = inject(CharacteristicValueService);
productVariants :ProductVariant[] =[];
displayedColumns: string[] = ['productVariantId', 'code', 'specificPrice', 'quantityInStock', 'productId','charcteristic','actions'];


loadProductVariants(){
  this.productVariantService.getProductVariant().subscribe({
    next:(variants)=>{
      console.log("Product Variants:", variants);
      this.productVariants= variants;
      variants.forEach(productVariant => {
        this.loadCharacteristics(productVariant.productVariantId);
      });
    },
    error:(error)=>{
      console.error("Error fetching product variants:", error);
    }
  });
}

ngOnInit(): void {
  this.loadProductVariants();
}

characteristicsMap: { [key: number]: any } = {};
loadCharacteristics(productVariantId: number){
  this.characteristicValueService.findCharacteristicValueListByProductVariantId(productVariantId).subscribe({
    next:(response)=>{
      console.log("Characteristic Values for Product Variant ID", productVariantId, ":", response);
      this.characteristicsMap[productVariantId] = response;
    },
    error:(error)=>{
      console.error("Error fetching characteristic values for Product Variant ID", productVariantId, ":", error);
    }
  });
}

editProductVariant(id:number){
  this.router.navigate(['/admin/productVariant/edit-productVariant',id]);
}


deleteProductVariant(id:number){
  Swal.fire({
    title: "Are you sure you want to delete this product Variant ?",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
  }).then((result) => {
    if(result.isConfirmed){
      this.productVariantService.deleteProductVariant(id).subscribe({ 
        next:(response)=>{
          console.log("Deleted! :",response)
          Swal.fire('Deleted!', 'The product Variant has been deleted.', 'success');
          this.loadProductVariants();
        },error:(error)=>{
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: error.error?.message || 'An error occurred while deleting!'
          });
        }
      });
    }
  }) 
  }
  addProductVariant(){
    this.router.navigate(['/admin/productVariant/add-productVariant']);
  }
  goBack(){
    this.location.back();
  }

}
