import { Component, OnInit, inject } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { Observable } from 'rxjs';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductService } from '../../../../../core/services/stockManagment/productService/product.service';
import { ProductVariantService } from '../../../../../core/services/stockManagment/productVariantService/product-variant.service';
import { Product } from '../../../../../shared/models/StockManagment/product.model';
import { ActivatedRoute } from '@angular/router';
import { ProductVariantDTO } from '../../../../../shared/models/dto/stockManagmentDTO/ProductVariant.dto';
import { Location } from '@angular/common';

@Component({
  selector: 'app-product-variant-edit',
  standalone: true,
  imports: [ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule, 
    MatInputModule,
    MatButtonModule,
    CommonModule,
    MatCardModule],
  templateUrl: './product-variant-edit.component.html',
  styleUrl: './product-variant-edit.component.css'
})
export class ProductVariantEditComponent  implements OnInit{
  private productVariantService = inject(ProductVariantService);
  private productService = inject(ProductService);
  private formBuilder = inject(FormBuilder);

  protected products$!:Observable<Product[]>;
  private location = inject(Location);

  private route = inject(ActivatedRoute);

  private id!: number;
  productVariantForm= this.formBuilder.group({
    code:[''],
    specificPrice:[''],
    quantityInStock:[''],
    productId:this.formBuilder.control<number | null>(null, Validators.required),
  })

  ngOnInit(): void {
    this.products$ = this.productService.getProducts();
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    console.log("id : ",this.id);
    if(this.id){
      this.productVariantService.findProductVariantById(this.id).subscribe({
        
        next:(productVariant)=>{
          console.log("productVariant:",productVariant);
        
          this.productVariantForm.patchValue({
            code:productVariant.code,
            specificPrice:String(productVariant.specificPrice),
            quantityInStock:String( productVariant.quantityInStock),
            productId:productVariant.product.productId,
        
          });
        },
        error:(err)=>{
          console.log("Error loading product",err);
        }
      });
    }
  }
  private mapFormToProductVaraint(): ProductVariantDTO {
    return this.productVariantForm.getRawValue() as unknown as ProductVariantDTO;
  }


  onSubmit(){
    if(this.productVariantForm.invalid)return;
    const productVariantDTO = this.mapFormToProductVaraint();
    this.productVariantService.editProductVariant(productVariantDTO,this.id).subscribe({
      next: (response) => {
        alert('Product variant edtied successfully');
        this.location.back();
       
      },
      error: (err) => {
        console.error('Error edting product', err);
      
        if (err.error?.message) {
          alert(err.error.message); 
        } else {
          alert('Erreur serveur lors de  editing du productVariant');
        }
        console.log("la response ",productVariantDTO);
      }
      
    });
  }
  goBack(){
    this.location.back();
  }
}
