import { Component, inject } from '@angular/core';
import { PurchaseOrderService } from '../../../../../core/services/PurchaseManagement/PurchaseOrder/purchase-order.service';
import { CommonModule, Location } from '@angular/common';
import { AbstractControl, FormArray, FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable} from 'rxjs';
import { Supplier } from '../../../../../shared/models/BusinessPartner/SupplierManagement/Suplier.model';
import { PurchaseOrderDTORequest } from '../../../../../shared/models/dto/PurchaseManagementDTO/PurchaseOrderDTORequest.dto';
import { MatSelectModule } from '@angular/material/select';
import { ProductVariantService } from '../../../../../core/services/stockManagement/productVariantService/product-variant.service';
import { DesignationRequest } from '../../../../../shared/models/Request/DesignationRequest';
import { ReferenceRequest } from '../../../../../shared/models/Request/ReferenceRequest';
import { CategoryRequest } from '../../../../../shared/models/Request/CategoryRequest';
import { Router } from '@angular/router';
import { ProductService } from '../../../../../core/services/stockManagement/productService/product.service';
import { Product } from '../../../../../shared/models/StockManagment/product.model';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { PurchaseOrderLineService } from '../../../../../core/services/PurchaseManagement/PurchaseOrderLine/purchase-order-line.service';
import { UnitService } from '../../../../../core/services/stockManagement/unitService/unit.service';
import { Unit } from '../../../../../shared/models/StockManagment/Unit.model';
import { FormStateService } from '../../../../../core/services/form-state.service';
import { SupplierService } from '../../../../../core/services/BusinessPartnerManagement/supplierManagement/supplier.service';

@Component({
  selector: 'app-purchase-order-create',
  standalone: true,
  imports: [CommonModule, 
    MatSelectModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    MatInputModule,
    MatCardModule,
    ReactiveFormsModule,
  MatTableModule],
  templateUrl: './purchase-order-create.component.html',
  styleUrl: './purchase-order-create.component.css'
})
export class PurchaseOrderCreateComponent {

  private purchaseOrderService =inject(PurchaseOrderService);
  private supplierService = inject(SupplierService);
  private productVariantService = inject(ProductVariantService);
  private productService = inject(ProductService);
  private fb = inject(FormBuilder);
  private location = inject(Location);
  
  protected suppliers$!:Observable<Supplier[]>;
  protected unitList$!:Observable<Unit[]>;
  private unitService = inject(UnitService);
  
  protected productList: Product[] = [];
  filteredProducts:Product[]=[];
  purchaseOrderId!:number;
  hasTyped = false;
  dataSource: any[] = [];
  purchaseOrderForm = this.fb.group({
    supplierId: [null,Validators.required],
    totalAmount: [],
    searchValue:[''],
    unitPriceSelection:['TTC'],
    discountType:['fixed'],
    lines: this.fb.array([])
  });

  VariantsSelected$ = this.productVariantService.variantsSelected$;
  formStateService = inject(FormStateService);



  ngOnInit(){
    this.suppliers$ = this.supplierService.getSuppliers();
    this.unitList$ = this.unitService.getUnits();
    this.purchaseOrderForm.get('searchValue')?.valueChanges
    .subscribe((value) => {
      this.hasTyped = value!.trim().length > 0;
      this.searchProducts();
    if(!this.hasTyped){
      this.filteredProducts = [];
    }
    });
    this.VariantsSelected$.subscribe(variants => {
      this.lines.clear(); 
      variants.forEach(variant => {
          const exists = this.lines.value.find(
         (line: any) => line.productVariantId === variant.productVariantId
         );
         
          this.lines.push(this.fb.group({
            productVariantId: [variant.productVariantId],
            quantity: [1, Validators.required],
            unitPrice: [0, Validators.required],
            discount: [0],
            unitId: [null],
            tax: [19, Validators.required],
            product: [variant.product],
            code: [variant.code]
        }));
      
      });
      // sig il ya une modication dans la liste des variants selected on met a jour la datasource de la table
      //met le dans une variable la nouvelle liste
      this.dataSource = [...this.lines.controls];
    });
    const savedData = this.formStateService.getProductForm();
    if(savedData){
      console.log("***********");
      console.log(savedData);
      console.log("***********");
      this.lines.patchValue(savedData);
    }
  }

  
  searchProducts() {
    const value = this.purchaseOrderForm.get('searchValue')?.value?.trim() ;
    if (!value) return;
    switch (this.selectedSearchType) {

      case 'Product_designation':
        this.productService.findProductByDesignation({
          productDesignation: value
        }).subscribe(res => { 
          this.filteredProducts = res; });
          break;
  
      case 'Product_category':
        this.productService.findProductByCategoryName({
          productCategoryName: value
        }).subscribe(res => {
          this.filteredProducts = res;
        });
        break;
  
      case 'Product_reference':
        this.productVariantService.findProductByReference({
          productReference: value
        }).subscribe(res => {
          this.filteredProducts = res;
        });
        break;
    }
  }
  selectProduct(product:Product){
    const value = this.getDisplayValue(product);
    this.purchaseOrderForm.get('searchValue')?.setValue(value);
  }

  mapfbToPurchaseOrderDTO():PurchaseOrderDTORequest{
    const form =this.purchaseOrderForm.getRawValue();
    return{
      supplierId: form.supplierId!,
    };
  }


  purchseOrderLineService= inject(PurchaseOrderLineService)
  showSearchBar = false;


  searchTypes =[ 'Product_designation' , 'Product_category' , 'Product_reference' ];
  selectedSearchType: string = 'Product_designation';
  searchValue: string = '';
  router= inject(Router);

  onSearchTypeChange() {
      this.purchaseOrderForm.get('searchValue')?.setValue('', {
        emitEvent: false
      });
      this.filteredProducts = [];
      this.hasTyped = false;
    }

  SearchProductVarinatBy(){
    const value = this.purchaseOrderForm.get('searchValue')?.value?.trim() ;
    if (!value) return;
    switch (this.selectedSearchType) {
      case 'Product_designation': {
        const request_designation: DesignationRequest = { productDesignation: value! };
        this.findProductVariantListByDesignation(request_designation);
        break;
      }
      case 'Product_category':
        const request_product_category: CategoryRequest = { productCategoryName: value!};
        this.findProductVariantListByCategoryName(request_product_category);
        break;
      case 'Product_reference':
        const request_reference: ReferenceRequest = { productReference: value!};
        this.findProductVariantListByReference(request_reference);
        break;
    }
  }

  getPlaceholder(): string {
    switch (this.selectedSearchType) {
      case 'Product_designation':
        return 'Search by designation';
      case 'Product_category':
        return 'Search by category';
      case 'Product_reference':
        return 'Search by reference';
      default:
        return 'Search...';
    }
  }
  getDisplayValue(product: Product): string {
    switch (this.selectedSearchType) {
      case 'Product_reference':
        return product.reference || 'No reference';
      case 'Product_designation':
        return product.designation || 'No designation';
      case 'Product_category':
        return product.category?.name || 'No category';
      default:
        return product.designation || 'No designation';
    }
  }
  //Impossible d'assigner le type 'string | undefined' au type 'string'.
  //Impossible d'assigner le type 'undefined' au type 'string'
  // en met || No category..
  toggleSearchBar() {
    this.showSearchBar = !this.showSearchBar;
  }
  
  /////////////////////////////////////////////////////
  searchByProductDesignation(designation: DesignationRequest) {
    this.productVariantService.findProductByDesignation(designation).subscribe({
      next:(products)=>{
        console.log("Successfully fetched products by designation ");
          this.productList = products;
          
      },
      error:()=>{
        console.log("Failed to fetch products by designation");
      }
    })
    
  }
  findProductVariantListByDesignation(designation:DesignationRequest){
    this.productVariantService.findProductVariantByProductDesignation(designation).subscribe({
      next:(variants)=>{
        console.log("Successful operation to find product variants by designation")
        this.productVariantService.setVariants(variants);
        this.router.navigate(['/admin/purchase-order/select-product-variants']);
      },
      error:()=>{
        console.error("Failed operation to find product variants by designation ");
      }
    });
  }
    /////////////////////////////////////////////////////
  searchByProductReference(reference: ReferenceRequest) {
    this.productVariantService.findProductByReference(reference).subscribe({
      next:(products)=>{
        console.log("Successfully fetched products by Reference ");
        this.productList = products;
      },
      error:()=>{
        console.log("Failed to fetch products by Reference");
      }
    })
  }
  findProductVariantListByReference(reference:ReferenceRequest){
    this.productVariantService.findProductVariantByProductReference(reference).subscribe({
      next:(variants)=>{
        console.log("Successful operation to find product variants by Reference")
        this.productVariantService.setVariants(variants);
        this.router.navigate(['/admin/purchase-order/select-product-variants']);
      },
      error:()=>{
        console.error("Failed operation to find product variants by Reference ");
        }
      });
  }
  /////////////////////////////////////////////////////
  searchProductByCategory(categoryName: CategoryRequest) {
    this.productVariantService.findProductByCategoryName(categoryName).subscribe({
      next:(products)=>{
        console.log("Successfully fetched products by Category ");
        this.productList = products;
      },
      error:()=>{
        console.log("Failed to fetch products by Category");
      }
    })
  }
  findProductVariantListByCategoryName(categoryName:CategoryRequest){
    this.productVariantService.findProductVariantByCategoryName(categoryName).subscribe({
      next:(variants)=>{
        console.log("Successful operation to find product variants by category");
        this.productVariantService.setVariants(variants);
        this.router.navigate(['/admin/purchase-order/select-product-variants']);
      },
      error:()=>{
        console.error("Failed operation to find product variants by category");
        
      }
    });
  }


  removeVariantSelected(variantId: number) {

    const index = this.lines.value.findIndex(
      (l: any) => l.productVariantId === variantId
    );
  
    if (index !== -1) {
      this.lines.removeAt(index);
    }
    this.productVariantService.removeVariantSelected(variantId);
  
  }
  displayedColumns: string[] = [ 'Product_designation','Product_variant_code','Product_reference','Product_quantity','Product_Unit','discount','Unit_Price','tax','delete'];


  
  get lines(): FormArray {
    return this.purchaseOrderForm.get('lines') as FormArray;
  }
  
  VariantsSelectedList: any[] = [];

  savePurchaseOrder(){
    const purchaseOrder = this.mapfbToPurchaseOrderDTO();

    this.purchaseOrderService.addPurchaseOrder(purchaseOrder).subscribe({
      next:(response)=>{
        console.log("first step of creatation PurchaseOrder successful")
        this.purchaseOrderId= response.purchaseOrderId;
        alert("ADD PurchaseOrder")
        const priceType = this.purchaseOrderForm.get('unitPriceSelection')?.value;
        const discountType=this.purchaseOrderForm.get('discountType')?.value;
        const lines = this.lines.value.map((line:any) => {

          let unitPriceHT: number | null = null;
          let unitPriceTTC: number | null = null;
      
          if (priceType === 'TTC') {
            unitPriceTTC = line.unitPrice;
          } else {
            unitPriceHT = line.unitPrice;
          }
          return {
            productVariantId: line.productVariantId,
            purchaseOrderId: this.purchaseOrderId,
            unitId: line.unitId,
            quantity: line.quantity,
            discount: line.discount,
            unitPriceHt: unitPriceHT,
            unitPriceTTC: unitPriceTTC,
            tax: line.tax,
          };
          
        });
        console.log("LINES TO SEND:", lines);
        if(discountType && discountType === 'percentage' ){
          this.purchseOrderLineService.addPurchaseOrderLineListWithPercentage(lines).subscribe({
            next:(res)=>{
              console.log("purchaseOrderListe WithPercentage added Suceefully");
              this.purchseOrderLineService.totalOfPurchaseOrder(this.purchaseOrderId).subscribe({
                next:(res)=>{
                  console.log("Total amount of PurchaseOrder updated successfully",res);
                },
                error:(err)=>{
                  console.log("Error in updating total amount of PurchaseOrder");
             }
             })
              
              this.productVariantService.resetVariantSelectedList();
              this.lines.clear(); 
            },
            error:(err)=>{
              console.log("Error in add PurchaseOrderList");
            }
          })
        }
        if(discountType && discountType === 'fixed' ){
          this.purchseOrderLineService.addPurchaseOrderLineListWithoutPercentage(lines).subscribe({
            next:(res)=>{
              console.log("purchaseOrderListe WithoutPercentage added Suceefully");
              this.purchseOrderLineService.totalOfPurchaseOrder(this.purchaseOrderId).subscribe({
                next:(res)=>{
                  console.log("Total amount of PurchaseOrder updated successfully",res);
                },
                error:(err)=>{
                  console.log("Error in updating total amount of PurchaseOrder");
             }
             })
              this.productVariantService.resetVariantSelectedList();
              this.lines.clear(); 
            },
            error:(err)=>{
              console.log("Error in add PurchaseOrderList");
            }
          })
        }
     
        
      },
      error:(err) => {
        alert("Error for creatation PurchaseOrder ")
        console.log("Purchase Order creation failed at first step ",err);
      }
    })
  }



 addSupplier(){
  this.router.navigate(['/admin/suppliers/add-supplier']);

 }
  goBack() {
    this.location.back();
  }
}
