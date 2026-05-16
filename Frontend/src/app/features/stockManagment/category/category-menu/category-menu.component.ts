import { Component, OnInit, inject } from '@angular/core';
import { CategoryService } from '../../../../core/services/stockManagement/categoryService/category.service';
import { CategoryTest } from '../../../../shared/models/StockManagment/CategoryTest.model';
import { CategorySelectorComponent } from "../category-selector/category-selector.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-category-menu',
  standalone: true,
  imports: [CategorySelectorComponent,CommonModule],
  templateUrl: './category-menu.component.html',
  styleUrl: './category-menu.component.css'
})
export class CategoryMenuComponent implements OnInit {

  roots: CategoryTest[] = [];
  activeRoot?: CategoryTest;

  categoryService = inject(CategoryService);

  ngOnInit(): void {
    this.categoryService.getCategoryTree().subscribe({
      next: (data) => {
        this.roots = data;
        this.activeRoot = data.length > 0 ? data[0] : undefined;
      },
      error: (err) => {
        console.error('Erreur chargement categories', err);
      }
    });
  }

  setActive(root: CategoryTest) {
    this.activeRoot = root;
  }
}
