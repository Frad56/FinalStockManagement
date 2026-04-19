import { Component, OnInit, inject } from '@angular/core';
import { Location } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { Observable } from 'rxjs';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductUnitSaleService } from '../../../../../core/services/stockManagment/productUnitSaleService/product-unit-sale.service';
import { ProductService } from '../../../../../core/services/stockManagment/productService/product.service';
import { UnitService } from '../../../../../core/services/stockManagment/unitService/unit.service';
import { Product } from '../../../../../shared/models/StockManagment/product.model';
import { Unit } from '../../../../../shared/models/StockManagment/Unit.model';
import { ProductUnitSaleDTO } from '../../../../../shared/models/dto/stockManagmentDTO/ProductUnitSale.dto';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-product-unit-sale-create',
  standalone: true,
  imports: [ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule, 
    MatInputModule,
    MatButtonModule,
    CommonModule,
    MatCardModule],
  templateUrl: './product-unit-sale-create.component.html',
  styleUrl: './product-unit-sale-create.component.css'
})
export class ProductUnitSaleCreateComponent  implements OnInit{

private productUnitSaleService= inject(ProductUnitSaleService);
private location = inject(Location);
private formBuilder = inject(FormBuilder);
private productService = inject(ProductService);
private unitService = inject(UnitService);

product!: Product;
private findProduct !:Observable<Product>;
private route = inject(ActivatedRoute);

protected products!:Observable<Product[]>;
protected units!:Observable<Unit[]>;

id!:number;

productUnitSaleForm = this.formBuilder.group({
  productId : [null as number | null, Validators.required],
  unitPrice :['',Validators.required],
  conversionFactor:['',Validators.required],
  unitId :[null as number | null, Validators.required]

})
ngOnInit() {
  const id = Number(this.route.snapshot.paramMap.get('id'));

  
  if (!id) return;

  this.productService.findProductById(id).subscribe(p =>{
    this.product =p;

  this.productUnitSaleForm.patchValue({
    productId:p.productId
    });
  });
  this.units = this.unitService.getUnits();

  this.id = Number(this.route.snapshot.paramMap.get('id'));

  if (this.id) {
    this.productUnitSaleForm.patchValue({
      productId: this.id
    });
  }
}

private mapFormToProductUnitSale():ProductUnitSaleDTO{
  return this.productUnitSaleForm.getRawValue() as unknown as ProductUnitSaleDTO;
}
onSubmit(){
  if(this.productUnitSaleForm.invalid) return;
  const productUnitSaleDTO  = this.mapFormToProductUnitSale();
  this.productUnitSaleService.addProductUnitSale(productUnitSaleDTO).subscribe({
    next :(response) =>{
      alert('product Unit Sale Created successfully');
      this.productUnitSaleForm.reset();

    },
    error :(error)=> {
      console.error('Error creating product', error,productUnitSaleDTO);

    }
  })
 
}

goBack(){
  this.location.back();
}
  
  
}
