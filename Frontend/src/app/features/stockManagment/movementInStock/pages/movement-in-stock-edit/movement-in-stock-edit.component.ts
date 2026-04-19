import { Component, OnInit, inject } from '@angular/core';
import { Location } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { UnitService } from '../../../../../core/services/stockManagment/unitService/unit.service';
import { ProductVariantService } from '../../../../../core/services/stockManagment/productVariantService/product-variant.service';
import { MovementInStockService } from '../../../../../core/services/stockManagment/movementInStockService/movement-in-stock.service';
import { Unit } from '../../../../../shared/models/StockManagment/Unit.model';
import { Observable } from 'rxjs';
import { ProductVariant } from '../../../../../shared/models/StockManagment/ProductVariant.model';
import { MovementInStockType } from '../../../../../shared/models/enum/MovementInStockType';
import { MovementInStockDTO } from '../../../../../shared/models/dto/stockManagmentDTO/MovementInStock.dto';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-movement-in-stock-edit',
  standalone: true,
  imports: [ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule, 
    MatInputModule,
    MatButtonModule,
    CommonModule,
    MatCardModule],
  templateUrl: './movement-in-stock-edit.component.html',
  styleUrl: './movement-in-stock-edit.component.css'
})
export class MovementInStockEditComponent implements OnInit{
private location = inject(Location);

private formBuilder = inject(FormBuilder);
private movementInStockService = inject(MovementInStockService);
private productVariantService = inject(ProductVariantService);
private unitService = inject(UnitService);

units$!:Observable<Unit[]>;
productVariants$!:Observable<ProductVariant[]>;

id!:number;
private route = inject(ActivatedRoute);

movementInStockTypes = Object.values(MovementInStockType).filter(value => isNaN(Number(value))); 

movementInStockForm =this.formBuilder.group({
  movementInStockType:['',Validators.required],
  quantityInStock:[0],
  productVariantId:[0,Validators.required],
  unitId:[null as number | null, Validators.required]
  
})
ngOnInit(): void {
    this.units$ = this.unitService.getUnits();
    this.productVariants$ =this.productVariantService.getProductVariant();
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if(this.id){
      this.movementInStockService.findMovementInStockById(this.id).subscribe({
        next:(movmentInStock)=>{
          console.log("movmentInStock:",movmentInStock);
          this.movementInStockForm.patchValue({
            movementInStockType : movmentInStock.movementInStockType,
            quantityInStock: movmentInStock.quantityInStock,
            productVariantId:movmentInStock.productVariant.productVariantId,
            unitId: movmentInStock.unit.unitId
          })
        }
      })
    }
  }
private mapFormToMovementInStocke():MovementInStockDTO{
  return this.movementInStockForm.getRawValue() as unknown as MovementInStockDTO;
}

onSubmit(){
  if(this.movementInStockForm.invalid) return;
  const movementInStockDTO = this.mapFormToMovementInStocke();
  this.movementInStockService.addMovementInStock(movementInStockDTO).subscribe({
    next:()=>{
      alert("Movement edited successfully")
      console.log("data:",movementInStockDTO)
      this.movementInStockForm.reset();
    },
    error:(err)=>{
      console.log('error',err)
    }
  })
}
goBack(){
  this.location.back();
}
}
