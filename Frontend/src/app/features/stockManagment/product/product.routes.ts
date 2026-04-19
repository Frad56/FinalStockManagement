import { Routes } from "@angular/router";
import { ProductListComponent } from "./pages/product-list/product-list.component";
import { ProductCreateComponent } from "./pages/product-create/product-create.component";
import { ProductEditComponent } from "./pages/product-edit/product-edit.component";

export const PRODUCT_ROUTES:Routes = [
    
    { path:'products', component :ProductListComponent},
    {path:'add-product',component:ProductCreateComponent},
    {path:'edit-product/:id',component:ProductEditComponent}
];