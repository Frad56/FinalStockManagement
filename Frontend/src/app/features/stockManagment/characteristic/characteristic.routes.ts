import { Routes } from "@angular/router";
import { CharacteristicEditComponent } from "./pages/characteristic-edit/characteristic-edit.component";
import { CharacteristicListComponent } from "./pages/characteristic-list/characteristic-list.component";
import { CharacteristicCreateComponent } from "./pages/characteristic-create/characteristic-create.component";



export const CHARACTERISTIC_ROUTES:Routes=[
 {path:'characteristic-list', component: CharacteristicListComponent},
 {path:'add-characteristic', component: CharacteristicCreateComponent},
 {path:'add-characteristic-with-productId/:id', component: CharacteristicCreateComponent},
 {path:'edit-characteristic/:id',component:CharacteristicEditComponent}
]