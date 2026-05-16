import { Component, EventEmitter, Input, OnInit, Output, inject } from '@angular/core';
import { CategoryTest } from '../../../../shared/models/StockManagment/CategoryTest.model';
import { CategoryService } from '../../../../core/services/stockManagement/categoryService/category.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-category-selector',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './category-selector.component.html',
  styleUrl: './category-selector.component.css'
})
export class CategorySelectorComponent  {

  @Input() categories: CategoryTest[] = [];
}