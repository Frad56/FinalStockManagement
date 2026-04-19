import { Unit } from "./Unit.model";
import { Product } from "./product.model";

export class ProductUnitSale{
    productUnitSaleId!:number;
    product!:Product;
    unit!:Unit;
    unitPrice!:number;
    conversionFactor!:number;

}