import { Routes } from "@angular/router";
import { CategoryCreateComponent } from "./pages/category-create/category-create.component";
import { CategoryListComponent } from "./pages/category-list/category-list.component";

export const CATEGORY_ROUTES:Routes = [
    
    { path:'category', component :CategoryListComponent},
    {path:'add-category',component:CategoryCreateComponent},
    {path:'edit-category/:id',component:CategoryCreateComponent}

];