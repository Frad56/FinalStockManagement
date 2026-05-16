import { Routes } from '@angular/router';
import { AdminComponent } from './features/users/admin/pages/admin.component';
import { AdminChangePasswordComponent } from './features/users/admin/pages/admin-change-password/admin-change-password.component';
import { AuthGuard } from './auth/guards/auth.guard';

export const routes: Routes = [

{path:'',redirectTo: 'login', pathMatch:'full'},
{path:'', loadChildren:() =>import('./auth/auth.routes').then(m => m.authRoutes)},


{path:'admin',component:AdminComponent,canActivate: [AuthGuard], data: { role: 'ADMIN' },children:[
    { path: 'dashboard', loadComponent: () => import('./features/dashboard/dashboard.component').then(m => m.DashboardComponent) },
    {path:'adminChangePassword',component: AdminChangePasswordComponent},
    {path:'', loadChildren:() =>import('./features/stockManagment/product/product.routes').then(m => m.PRODUCT_ROUTES)},

    {path:'category', loadChildren:() => import('./features/stockManagment/category/category.routes').then(m => m.CATEGORY_ROUTES)},


    {path:'suppliers', loadChildren:() =>import('./features/BusinessPartnerManagement/supplierManagement/supplier/supplier.routes').then(m => m.SUPPLIER_ROUTES)},
    {path:'client', loadChildren:() =>import('./features/BusinessPartnerManagement/clientManagement/client.routes').then(m => m.CLIENT_ROUTES)},


    {path:'product-suppliers', loadChildren:() =>import('./features/BusinessPartnerManagement/supplierManagement/productSupplier/productSupplier.routes').then(m => m.PRODUCT_SUPPLIER_ROUTES)},
    {path:'aisle',loadChildren:()=>import('./features/stockManagment/Aisle/aisle.routes').then(m => m.Aisle_ROUTES)},
    {path:'characteristic',loadChildren:()=>import('./features/stockManagment/characteristic/characteristic.routes').then(m => m.CHARACTERISTIC_ROUTES)},

    {path:'characteristicValue',loadChildren:()=>import('./features/stockManagment/characteristicValue/characteristicValue.routes').then(m => m.CHARACTERISTIC_VALUE_ROUTES)},

    {path:'movementInStock',loadChildren:()=>import('./features/stockManagment/movementInStock/movementInStock.routes').then(m => m.MOVEMENT_IN_STOCK_ROUTES)},

    {path:'productCharacteristic',loadChildren:()=>import('./features/stockManagment/productCharacteristic/productCharacteristic.routes').then(m => m.PRODUCT_CHARACTERISTIC_ROUTES)},


    {path:'productUnitSale',loadChildren:()=>import('./features/stockManagment/productUnitSale/productUnitSale.routes').then(m => m.PRODUCT_UNIT_SALE_ROUTES)},

    {path:'productVariant',loadChildren:()=>import('./features/stockManagment/productVariant/productVariant.routes').then(m => m.PRODUCT_VARIANT_ROUTES)},


    {path:'unit',loadChildren:()=>import('./features/stockManagment/unit/unit.routes').then(m => m.Unit_ROUTES)},

    {
        path: 'purchase-order',
        loadChildren: () =>
          import('./features/PurchaseManagement/AdminPurchaseOrder/pages/adminPurchaseOrder.routes')
            .then(m => m.ADMIN_PURCHASE_ORDER_ROUTES)
      },
      

      {path:'sale',loadChildren:()=> import('./features/salesManagement/salesManagement.routes').then(m=>m.SALES_MANAGEMENT_ROUTES)}
      



    ]}


];
