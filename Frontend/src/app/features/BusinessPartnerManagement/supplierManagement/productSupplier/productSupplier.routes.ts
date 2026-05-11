import { Routes } from "@angular/router";
import { ListProductSupplierComponent } from "./pages/list-product-supplier/list-product-supplier.component";
import { CreateProductSupplierComponent } from "./pages/create-product-supplier/create-product-supplier.component";
import { UpdateProductSupplierComponent } from "./pages/update-product-supplier/update-product-supplier.component";

export const PRODUCT_SUPPLIER_ROUTES :Routes= [
    {path:'product-suppliers', component:ListProductSupplierComponent},
    {path:'add-product-supplier', component:CreateProductSupplierComponent},
    {path:'update-product-supplier/:id', component:UpdateProductSupplierComponent}
]