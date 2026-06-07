import { Component, OnInit, inject } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { ActivatedRoute, Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule, Location } from '@angular/common';
import { Observable } from 'rxjs';
import { ProductUnitSale } from '../../../../../shared/models/StockManagment/ProductUnitSale.model';
import { ProductUnitSaleService } from '../../../../../core/services/stockManagement/productUnitSaleService/product-unit-sale.service';

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
private route = inject(ActivatedRoute);      


productUnitSales$! : Observable<ProductUnitSale[]>;
displayedColumns: string[] = ['productUnitSaleId', 'productDescription','productReference', 'unit', 'unitPrice', 'conversionFactor','actions'];
private productUnitSaleService  = inject(ProductUnitSaleService);
private router = inject(Router);
productId!: number;


  loadProductUnitSale() {
    if (this.productId) {
      this.productUnitSales$ = this.productUnitSaleService.findProductUnitSaleByProductId(this.productId) as any;
    } else {
      this.productUnitSales$ = this.productUnitSaleService.getAllProductUnitSale();
    }
  }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.productId = +idParam;
    }
    this.loadProductUnitSale();
  }




deleteProductUnitSale(id:number){
  this.productUnitSaleService.deleteProductUnitSale(id).subscribe(res => {
    alert("product Unit Sale Deleted !");
    this.loadProductUnitSale();
  });
 
}
addProductUnitSale() {
  if (this.productId) {
    this.router.navigate(['/admin/productUnitSale/add-productUnitSale-with-ProductId', this.productId]);
  } else {
    this.router.navigate(['admin/productUnitSale/add-productUnitSale']);
  }
}
editProductUnitSale(productUnitSaleId:number){
  this.router.navigate(['admin/productUnitSale/edit-productUnitSale',productUnitSaleId]);

}


goBack(){
  this.location.back();
}
  
}
