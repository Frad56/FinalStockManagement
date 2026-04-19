import { Routes } from "@angular/router";
import { MovementInStockCreateComponent } from "./pages/movement-in-stock-create/movement-in-stock-create.component";
import { MovementInStockListComponent } from "./pages/movement-in-stock-list/movement-in-stock-list.component";
import { MovementInStockEditComponent } from "./pages/movement-in-stock-edit/movement-in-stock-edit.component";

export const MOVEMENT_IN_STOCK_ROUTES:Routes = [
    
    { path:'movementInStock-list', component :MovementInStockListComponent},
    {path:'add-movementInStock',component:MovementInStockCreateComponent},
    {path:'edit-movementInStock/:id',component:MovementInStockEditComponent}
];