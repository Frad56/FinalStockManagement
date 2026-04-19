import { Component, OnInit, inject } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule, Location } from '@angular/common';
import { Observable } from 'rxjs';
import { ProductUnitSale } from '../../../../../shared/models/StockManagment/ProductUnitSale.model';
import { ProductUnitSaleService } from '../../../../../core/services/stockManagment/productUnitSaleService/product-unit-sale.service';

@Component({
  selector: 'app-product-unit-sale-list',
  standalone: true,
  imports: [CommonModule
    ,MatTableModule,
     MatCardModule,
     MatIconModule,
     MatButtonModule],
  templateUrl: './product-unit-sale-list.component.html',
  styleUrl: './product-unit-sale-list.component.css'
})
export class ProductUnitSaleListComponent  implements OnInit{
private location = inject(Location);


productUnitSales$! : Observable<ProductUnitSale[]>;
displayedColumns: string[] = ['productUnitSaleId', 'productDescription','productReference', 'unit', 'unitPrice', 'conversionFactor','actions'];
private productUnitSaleService  = inject(ProductUnitSaleService);
private router = inject(Router);


loadProductUnitSale(){
  this.productUnitSales$ = this.productUnitSaleService.getAllProductUnitSale();
}
ngOnInit(): void {
  this.loadProductUnitSale();
}



deleteProductUnitSale(id:number){
  this.productUnitSaleService.deleteProductUnitSale(id).subscribe(res => {
    alert("product Unit Sale Deleted !");
    this.loadProductUnitSale();
  });
 
}
addProductUnitSale(){
  this.router.navigate(['productUnitSale/add-productUnitSale']);

}
editProductUnitSale(id:number){
  this.router.navigate(['productUnitSale/edit-productUnitSale',id]);

}
goBack(){
  this.location.back();
}
  
}
