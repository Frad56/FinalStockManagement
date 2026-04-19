import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CategoryCreateComponent } from "./features/stockManagment/category/pages/category-create/category-create.component";
import { CategoryListComponent } from "./features/stockManagment/category/pages/category-list/category-list.component";
import { ProductListComponent } from "./features/stockManagment/product/pages/product-list/product-list.component";
import { ProductCreateComponent } from "./features/stockManagment/product/pages/product-create/product-create.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Frontend';
}
