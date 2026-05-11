import { CommonModule, Location } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { ProductVariantService } from '../../../../../core/services/stockManagement/productVariantService/product-variant.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ProductVariant } from '../../../../../shared/models/StockManagment/ProductVariant.model';
import { CharacteristicValueService } from '../../../../../core/services/stockManagement/CharacteristicValueService/characteristic-value.service';
import { CharacteristicService } from '../../../../../core/services/stockManagement/characteristicService/characteristic.service';
import { ProductCharacteristic } from '../../../../../shared/models/StockManagment/ProductCharacteristic.model';
import { ProductCharacteristicService } from '../../../../../core/services/stockManagement/productCharacteristicService/product-characteristic.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-product-variants-by-product',
  standalone: true,
  imports: [CommonModule
    ,MatTableModule,
     MatCardModule,
     MatIconModule,
     MatButtonModule],
  templateUrl: './product-variants-by-product.component.html',
  styleUrl: './product-variants-by-product.component.css'
})
export class ProductVariantsByProductComponent  implements OnInit{

  private productId!:number;
  private location = inject(Location);
  private productVariantService = inject(ProductVariantService);
  private route = inject(ActivatedRoute);
  productVariants$!: Observable<ProductVariant[]>;
  displayedColumns: string[] = ['productVariantId', 'productId', 'code', 'specificPrice', 'quantityInStock','actions'];
 

  loadProductVariantsByProduct(){
    this.productId = Number(this.route.snapshot.paramMap.get('id'));
    if(this.productId){
      this.productVariants$ = this.productVariantService.
      findProductVariantbyProductId(this.productId);
    }    
  }

  ngOnInit(): void {
  this.loadProductVariantsByProduct();
  }

  addProductVariant(){
      window.location.href = `/admin/productVariant/add-productVariant-with-productId/${this.productId}`;
  }
  editProductVariant(productVariantId:number){
      window.location.href = `/admin/productVariant/edit-productVariant/${productVariantId}`;
  }

  deleteProductVariant(productVariantId:number){  
    Swal.fire({
      title: "Are you sure you want to delete this product ?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
    }).then((result) => {
      if(result.isConfirmed){
        this.productVariantService.deleteProductVariant(productVariantId).subscribe({ 
          next:(response)=>{
            console.log("Deleted! :",response)
            Swal.fire('Deleted!', 'The product Variant has been deleted.', 'success');
            this.loadProductVariantsByProduct();
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
  goBack(){
    this.location.back();
  }

}
