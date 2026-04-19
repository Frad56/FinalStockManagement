import { Routes } from "@angular/router";
import { CharacteristicValueEditComponent } from "./pages/characteristic-value-edit/characteristic-value-edit.component";
import { CharacteristicValueListComponent } from "./pages/characteristic-value-list/characteristic-value-list.component";
import { CharacteristicValueCreateComponent } from "./pages/characteristic-value-create/characteristic-value-create.component";

export const CHARACTERISTIC_VALUE_ROUTES:Routes = [
    
    { path:'characteristicValue-list', component :CharacteristicValueListComponent},
    {path:'add-characteristicValue',component:CharacteristicValueCreateComponent},
    {path:'edit-characteristicValue/:id',component:CharacteristicValueEditComponent}
];