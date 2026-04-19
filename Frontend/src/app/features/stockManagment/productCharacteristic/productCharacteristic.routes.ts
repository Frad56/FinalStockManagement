import { Routes } from "@angular/router";
import { ProductCharacteristicListComponent } from "./pages/product-characteristic-list/product-characteristic-list.component";
import { ProductCharacteristicCreateComponent } from "./pages/product-characteristic-create/product-characteristic-create.component";
import { ProductCharacteristicEditComponent } from "./pages/product-characteristic-edit/product-characteristic-edit.component";

export const PRODUCT_CHARACTERISTIC_ROUTES:Routes = [
    
    { path:'productCharacteristic-list', component :ProductCharacteristicListComponent},
    {path:'add-productCharacteristic',component:ProductCharacteristicCreateComponent},
    {path:'edit-productCharacteristic/:id',component:ProductCharacteristicEditComponent}
];