import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';

import { NavigationEnd, Router, RouterLink, RouterOutlet } from '@angular/router';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';
import { ChangeEmailComponent } from './change-email/change-email.component';
import { AdminResetEmailService } from '../../../../auth/service/adminResetEmail/admin-reset-email.service';
import { AuthService } from '../../../../auth/service/auth.service';
import { CategoryMenuComponent } from "../../../stockManagment/category/category-menu/category-menu.component";




@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [MatSidenavModule, MatButtonModule, CommonModule, RouterOutlet, CategoryMenuComponent, MatIconModule,RouterLink],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})


export class AdminComponent {
  showStockManagment = false;
  showProductManagment = false;
  showUserManagment =false;
  showCategoryManagment =false;
  showAisleManagment =false;
  showSupplierManagment = false;
  showClientManagment = false;
  showcharacteristicManagment=false;
  showUnitManagment= false;
  showProductUnitsaleManagment = false;
  showProductVariantManagment =false;
  showMovmentInStockManagment= false;
  showSaleManagment= false;
  showMenu = false;
  showCategoryMenu = true;
  router = inject(Router);

  private dialog = inject(MatDialog);
  private adminResetEmailService = inject(AdminResetEmailService);
  private authService = inject(AuthService);

  hideCategoryMenu() {
    this.showCategoryMenu = false;
  }
  ngOnInit(): void {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.showCategoryMenu = false;
      }
    });
    const isEmailChanged = this.authService.getIsEmailChanged();
    console.log('isEmailChanged:', isEmailChanged);
    if (isEmailChanged  === 'false' && localStorage.getItem('role') === 'ADMIN') {
      alert(isEmailChanged)
      this.openChangeEmailDialog();
    }
  }
  openChangeEmailDialog() {
    const dialogRef = this.dialog.open(ChangeEmailComponent, {
      width: '400px',
      disableClose: true 
    });
  
    dialogRef.afterClosed().subscribe(() => {
      localStorage.setItem('isFirstLogin', 'true');
    });
  }


//* ****************   SignUP    ************************** */
signUp(){
  this.router.navigate(['/admin/add-user']);
}


//////////////////// Stock Managment ////////////////////////
//Products
 getProducts(){
    this.router.navigate(['/admin/products']);
 }
 
 getCategory(){
  this.router.navigate(['/admin/category/menu']);
}
//

 addProduct(){
  this.router.navigate(['/admin/add-product']);
 }


 //add Product Supplier 
  addProductSupplier(){
    this.router.navigate(['/admin/product-suppliers/add-product-supplier']);
  }

 //Categorys
 addCategory(){
  this.router.navigate(['/admin/categorys/add-category']);
 }


 //Stocks

 addStock(){
  this.router.navigate(['/admin/stock/add-stock']);
 }

 ////////////////////////// supplier managment /////////////////////////////
 addSupplier(){
  this.router.navigate(['/admin/suppliers/add-supplier']);
 }
 allSuppliers(){
  this.router.navigate(['/admin/suppliers/suppliers']);
 }

 ////////////ClientManagment
 addClient(){
  this.router.navigate(['/admin/client/add-client']);
 }
 allClients(){
  this.router.navigate(['/admin/client/client-all']);
 }
  ////////////////////////// Aisle managment /////////////////////////////
  addAisle(){
    this.router.navigate(['/admin/aisle/add-aisle']);
   }
   allAisle(){
    this.router.navigate(['/admin/aisle/list-aisle']);
   }

 
   ////////////////////////// Characteristic managment /////////////////////////////

   addCharacteristic(){
    this.router.navigate(['/admin/characteristic/add-characteristic']);

   }
   allCharacteristic(){
    this.router.navigate(['/admin/characteristic/characteristic-list']);

   }
    ////////////////////////// Unit managment /////////////////////////////

    addUnit(){
      this.router.navigate(['/admin/unit/add-unit']);
  
     }
     allUnits(){
      this.router.navigate(['/admin/unit/unit-list']);
     }
    /////////////////////    ProductUnitsale  Managment                  ///////////////////////////////
    addProductUnitsale(){
      this.router.navigate(['/admin/productUnitSale/add-productUnitSale']);
  
     }
     allProductUnitsale(){
      this.router.navigate(['/admin/productUnitSale/productUnitSale-list']);
  
     }

     
     ////////////////////   ProductVariantManagment ///////////////////////////
     addProductVariant(){
      this.router.navigate(['/admin/productVariant/add-productVariant']);
  
     }
     allProductVariant(){
      this.router.navigate(['/admin/productVariant/productVariant-list']);
  
     }

      ////////////////////   Characteristic Value Managment ///////////////////////////
      addCharacteristicValue(){
        this.router.navigate(['/admin/characteristicValue/add-characteristicValue']);
    
       }
       allCharacteristicValue(){
        this.router.navigate(['/admin/characteristicValue/characteristicValue-list']);
    
       }

          ////////////////////   Movment In Stock Managment ///////////////////////////

      addMovmentInStock(){
        this.router.navigate(['/admin/movementInStock/add-movementInStock']);
    
      }
      allMovmentInStock(){
        this.router.navigate(['/admin/movementInStock/movementInStock-list']);
    
      }
      NotDeliveredPurchaseOrders(){
        this.router.navigate(['/admin/purchase-order/not-delivered-purchase-orders']);
      }

      addSale(){
        this.router.navigate(['/admin/sale/sales']);
      }

      salesOrderList(){
        this.router.navigate(['/admin/sale/sales-list']);

      }
  
      addPurchaseOrder(){
        this.router.navigate(['/admin/purchase-order/add-purchase-order']);

      }
      
      seeButtons(){
        this.showMenu = !this.showMenu;
      }

      changePassword(){
        this.router.navigate(['/admin/adminChangePassword']);
      }
      logout(){
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        localStorage.removeItem('isFirstLogin');
        this.router.navigate(['/login']);
      }
}
