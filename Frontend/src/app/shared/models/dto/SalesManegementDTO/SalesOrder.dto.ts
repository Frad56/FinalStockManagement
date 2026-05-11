import { PaymentType } from "../../enum/paymentType";
import { SalesOrderLineDTO } from "./SalesOrderLine.dto";

export interface SalesOrderDTO{

    clientId?:number;
    paymentType:PaymentType;
    salesOrderLineListDTO: SalesOrderLineDTO[];
}