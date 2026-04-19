
import { Routes } from "@angular/router";
import { UnitCreateComponent } from "./pages/unit-create/unit-create.component";
import { UnitListComponent } from "./pages/unit-list/unit-list.component";
import { UnitEditComponent } from "./pages/unit-edit/unit-edit.component";

export const Unit_ROUTES:Routes = [
    
    { path:'unit-list', component :UnitListComponent},
    {path:'add-unit',component:UnitCreateComponent},
    {path:'edit-unit/:id',component:UnitEditComponent}
];