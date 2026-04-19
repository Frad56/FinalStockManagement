import Decimal from "decimal.js";

export interface PurchaseOrderLineDTO{

    purchaseOrderId:number;
    productVariantId:number;
    quantity:number;
    unitPrice:number;
}