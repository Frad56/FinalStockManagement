import { Component, OnInit, computed, signal } from '@angular/core';
import {ProductDTO} from '../../../../../shared/models/dto/stockManagmentDTO/product.dto';
import { ProductService } from '../../../../../core/services/stockManagment/productService/product.service';
import { inject } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { Observable } from 'rxjs';
import { Category } from '../../../../../shared/models/StockManagment/Category.model';
import { CategoryService } from '../../../../../core/services/stockManagment/categoryService/category.service';
import { Location } from '@angular/common';
import { AisleService } from '../../../../../core/services/stockManagment/aisleService/aisle.service';
import { Aisle } from '../../../../../shared/models/StockManagment/Aisle.model';
import { MatIcon } from '@angular/material/icon';
import { ActivatedRoute, Router } from '@angular/router';
import { FormStateService } from '../../../../../core/services/form-state.service';
import {FormsModule} from '@angular/forms';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { CharacteristicService } from '../../../../../core/services/stockManagment/characteristicService/characteristic.service';
import { Characteristic } from '../../../../../shared/models/StockManagment/Characteristic.model';
import { Product } from '../../../../../shared/models/StockManagment/product.model';
import { ProductCharacteristicService } from '../../../../../core/services/stockManagment/productCharacteristicService/product-characteristic.service';
import Swal from 'sweetalert2';


export interface CharacteristicItem {
  id: number;
  name: string;
  completed:boolean;
}

@Component({
  selector: 'app-product-create',
  standalone: true,
  imports: [ReactiveFormsModule,
            MatFormFieldModule,
            MatSelectModule, 
            MatInputModule,
            MatButtonModule,
            CommonModule,
            MatCardModule,
          MatIcon,MatCheckboxModule, FormsModule],
  templateUrl: './product-create.component.html',
  styleUrl: './product-create.component.css'
})

export class ProductCreateComponent implements OnInit{

  
  private formStateService = inject(FormStateService);
  categorys!:Observable<Category[]>;
  aisles!:Observable<Aisle[]>;
  characteristics!:Observable<Characteristic[]>;
  private productService  = inject(ProductService);
  private categoryService = inject(CategoryService);
  private location = inject(Location);

  
  private characteristicService = inject(CharacteristicService);

  readonly characteristicsState = signal({
    completed: false,
    items: [] as CharacteristicItem[],
  });



  private router = inject(Router);
  private aisleService = inject(AisleService);
  fromBuilder= inject(FormBuilder);

  private productCharacteristicService =inject(ProductCharacteristicService);

  productForm = this.fromBuilder.group({
    reference : [''],
    designation:[''],
    brand:[''],
    description: [''],
    basePrice: ['', [Validators.required, Validators.min(0)]],
    categoryId:['',Validators.required],
    aisleId:['',Validators.required]
  });

  ngOnInit(){
    this.categorys=this.categoryService.leafCategoryList();
    this.aisles =this.aisleService.getAisles();
    console.log( "###########")
    console.log( this.categorys)
    const savedData = this.formStateService.getProductForm();
    if(savedData){
      console.log("***********");
      console.log(savedData);
      console.log("***********");
      this.productForm.patchValue(savedData);
    }

    this.characteristics = this.characteristicService.getCharacteristic();
    this.characteristics.subscribe(res => {
      this.characteristicsState.set({
        completed: false,
        items: res.map(characteristic => ({
          id: characteristic.characteristicId,
          name: characteristic.name,
          completed: false
        }))
      })
    });
  }
  readonly partiallyComplete = computed(() => {
    const state = this.characteristicsState();
    return state.items.some(i => i.completed) &&
          !state.items.every(i => i.completed);
  });

  update(completed: boolean, index?: number) {
    this.characteristicsState.update(state => {
      if (index === undefined) {
      
        state.completed = completed;
        state.items.forEach(i => i.completed = completed);
      } else {
    
        state.items[index].completed = completed;
        state.completed = state.items.every(i => i.completed);
      }
      return { ...state };
    });
  }
  
  
 

  private mapFormToProduct(): ProductDTO {
    return this.productForm.getRawValue() as unknown as ProductDTO;
  }


  createProduct() {
    if(this.productForm.invalid) return;
    const productDTO = this.mapFormToProduct();
    const selected = this.characteristicsState()
    .items
    .filter(i => i.completed)
    .map(i => i.id);
    this.productService.addProduct(productDTO).subscribe({
      next: (response) => {
        console.log('Product Created successfully', response);
        const productId = response.productId;
        if(!productId){
          alert('Product created but no ID returned');
          return;
        }
        this.productCharacteristicService
        .addListProductCharacteristic(selected, productId)
        .subscribe({
          next: (res) => {
            console.log("List ProductCharacteristic Created successfully", res);
          },
          error: (err) => {
            console.error("Erreur", err);
            alert(err.error?.message);
          }
        });     
        Swal.fire('Product Created successfully');   
        this.productForm.reset();
        this.formStateService.clearProductForm(); 
      },
      error: (err) => {
        console.error('Error creating product', err);
        if (err.error?.message) {
          alert(err.error.message);
        } else {
          alert('Erreur serveur lors création   product');
        }
        console.log("la response ",productDTO);
      }
    });
  }
 
  AddCharacteristic(){
    this.formStateService.setProductForm(this.productForm.value);
    this.router.navigate(['/admin/characteristic/add-characteristic']);
  }
  addCategory(){
  this.formStateService.setProductForm(this.productForm.value);
  this.router.navigate(['/admin/categorys/add-category']);
  }
  
 addAisle(){
   this.formStateService.setProductForm(this.productForm.value);
  this.router.navigate(['/admin/aisle/add-aisle']);
 }
goBack(){
  this.location.back();
}
  
  

}
