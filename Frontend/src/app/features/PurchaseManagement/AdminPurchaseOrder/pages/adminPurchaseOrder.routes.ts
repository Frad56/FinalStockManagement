import { Routes } from "@angular/router";
import { AdminPurchaseOrderListComponent } from "./admin-purchase-order-list/admin-purchase-order-list.component";
import { PurchaseOrderCreateComponent } from "./purchase-order-create/purchase-order-create.component";

export const ADMIN_PURCHASE_ORDER_ROUTES:Routes=[

    {path:'add-purchase-order',component:PurchaseOrderCreateComponent},
    {path:'select-product-variants',component:AdminPurchaseOrderListComponent}

]