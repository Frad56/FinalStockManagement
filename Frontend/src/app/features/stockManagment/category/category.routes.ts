import { Routes } from "@angular/router";
import { CategoryCreateComponent } from "./pages/category-create/category-create.component";
import { CategoryListComponent } from "./pages/category-list/category-list.component";
import { CategorySelectorComponent } from "./category-selector/category-selector.component";
import { CategoryMenuComponent } from "./category-menu/category-menu.component";
import { CategoryEditComponent } from "./pages/category-edit/category-edit.component";

export const CATEGORY_ROUTES:Routes = [
    
    { path:'category', component :CategoryListComponent},
    {path:'add-category',component:CategoryCreateComponent},
    {path:'edit-category/:id',component:CategoryEditComponent},
    {path:'category-selector',component:CategorySelectorComponent},
    {path:'menu',component:CategoryMenuComponent}

];