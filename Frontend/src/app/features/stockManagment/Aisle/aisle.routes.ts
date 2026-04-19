import { Routes } from "@angular/router";
import { AisleListComponent } from "./pages/aisle-list/aisle-list.component";
import { AisleCreateComponent } from "./pages/aisle-create/aisle-create.component";
import { AisleEditComponent } from "./pages/aisle-edit/aisle-edit.component";


export const Aisle_ROUTES:Routes=[
 {path:'list-aisle', component:AisleListComponent },
 {path:'add-aisle', component:AisleCreateComponent },
 {path:'edit-aisle/:id',component:AisleEditComponent}
]