import Decimal from "decimal.js";
import { ProductVariant } from "../StockManagment/ProductVariant.model";
import { PurchaseOrder } from "./PurchaseOrder.model";
import { Unit } from "../StockManagment/Unit.model";

export class PurchaseOrderLine{

    purchaseOrderLineId!:number;
    purchaseOrder!:PurchaseOrder;
    productVariant!:ProductVariant;
    unit!:Unit;
    quantity!:number;
    unitPrice!:number;
    discount!:number;
}