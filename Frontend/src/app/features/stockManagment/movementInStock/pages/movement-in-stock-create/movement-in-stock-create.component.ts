import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MovementInStockService } from '../../../../../core/services/stockManagment/movementInStockService/movement-in-stock.service';
import { UnitService } from '../../../../../core/services/stockManagment/unitService/unit.service';
import { Observable } from 'rxjs';
import { Unit } from '../../../../../shared/models/StockManagment/Unit.model';
import { MovementInStockDTO } from '../../../../../shared/models/dto/stockManagmentDTO/MovementInStock.dto';
import { Location } from '@angular/common';
import { ProductVariant } from '../../../../../shared/models/StockManagment/ProductVariant.model';
import { ProductVariantService } from '../../../../../core/services/stockManagment/productVariantService/product-variant.service';
import { MovementInStock } from '../../../../../shared/models/StockManagment/MovementInStock.model';
import { MovementInStockType } from '../../../../../shared/models/enum/MovementInStockType';


@Component({
  selector: 'app-movement-in-stock-create',
  standalone: true,
  imports: [ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule, 
    MatInputModule,
    MatButtonModule,
    CommonModule,
    MatCardModule],
  templateUrl: './movement-in-stock-create.component.html',
  styleUrl: './movement-in-stock-create.component.css'
})
export class MovementInStockCreateComponent implements OnInit {

private formBuilder = inject(FormBuilder);
private movementInStockService = inject(MovementInStockService);
private productVariantService = inject(ProductVariantService);
private unitService = inject(UnitService);
private location =inject(Location);

units$!:Observable<Unit[]>;
productVariants$!:Observable<ProductVariant[]>;

movementInStockTypes = Object.values(MovementInStockType).filter(value => isNaN(Number(value))); 

movementInStockForm =this.formBuilder.group({
  movementInStockType:['',Validators.required],
  quantityInStock:[''],
  productVariantId:[null,Validators.required],
  unitId:[null,Validators.required]
  
})
ngOnInit(): void {
    this.units$ = this.unitService.getUnits();
    this.productVariants$ =this.productVariantService.getProductVariant();
}

private mapFormToMovementInStocke():MovementInStockDTO{
  return this.movementInStockForm.getRawValue() as unknown as MovementInStockDTO;
}
onSubmit(){
  if(this.movementInStockForm.invalid) return;
  const movementInStockDTO = this.mapFormToMovementInStocke();
  this.movementInStockService.addMovementInStock(movementInStockDTO).subscribe({
    next:()=>{
      console.log("data:",movementInStockDTO)
      alert("movement In Stock created successfully")
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
