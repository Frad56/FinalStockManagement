import { Routes } from "@angular/router";
import { ProductVariantSearchByCodeComponent } from "./product-variant-search-by-code/product-variant-search-by-code.component";
import { CartComponent } from "./cart/cart.component";
import { CheckoutComponent } from "./checkout/checkout.component";
import { SalesComponent } from "./sales/sales.component";
import { SalesListComponent } from "./sales-list/sales-list.component";
import { EditSalesOrderListComponent } from "./edit-sales-order-list/edit-sales-order-list.component";
import { EditSaleOrderLineComponent } from "./edit-sale-order-line/edit-sale-order-line.component";
import { AddItemToSaleOrderComponent } from "./add-item-to-sale-order/add-item-to-sale-order.component";

export const SALES_MANAGEMENT_ROUTES:Routes=[

{path:'cart',component:CartComponent},
{path:'chekout',component:CheckoutComponent},
{path:'search-product-variant-by-code',component:ProductVariantSearchByCodeComponent},
{path:'sales',component:SalesComponent},
{path:'sales-list',component:SalesListComponent},

{path:'edit-sales-Oder-list/:id',component:EditSalesOrderListComponent},

{path:'edit-sales-Oder-line/:id',component:EditSaleOrderLineComponent},

{path:'add-item-to-sale-order/:id',component:AddItemToSaleOrderComponent}


    //PurchaseOrderListEditComponent

]