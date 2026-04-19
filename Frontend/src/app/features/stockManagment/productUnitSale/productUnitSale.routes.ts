
import { Routes } from "@angular/router";
import { ProductUnitSaleListComponent } from "./pages/product-unit-sale-list/product-unit-sale-list.component";
import { ProductUnitSaleCreateComponent } from "./pages/product-unit-sale-create/product-unit-sale-create.component";
import { ProductUnitSaleEditComponent } from "./pages/product-unit-sale-edit/product-unit-sale-edit.component";

export const PRODUCT_UNIT_SALE_ROUTES:Routes = [
    
    { path:'productUnitSale-list', component :ProductUnitSaleListComponent},
    {path:'add-productUnitSale',component:ProductUnitSaleCreateComponent},
    {path:'add-productUnitSale-with-ProductId/:id',component:ProductUnitSaleCreateComponent},
    {path:'edit-productUnitSale/:id',component:ProductUnitSaleEditComponent}
];