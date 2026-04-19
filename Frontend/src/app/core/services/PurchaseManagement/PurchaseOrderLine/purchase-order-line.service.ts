import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { PurchaseOrderLine } from '../../../../shared/models/PurchaseManagement/PurchaseOrderLine.model';
import { PurchaseOrderLineDTO } from '../../../../shared/models/dto/PurchaseManagementDTO/PurchaseOrderLine.dto';
import { PurchaseOrderLineRequest } from '../../../../shared/models/Request/PurchaseOrderLineRequest';

@Injectable({
  providedIn: 'root'
})
export class PurchaseOrderLineService {

  private apiUrl = 'http://localhost:8080/api/purchaseOrderLine';
  private http = inject(HttpClient);

  getPurchaseOrderLineList():Observable<PurchaseOrderLine[]>{
    return this.http.get<PurchaseOrderLine[]>(`${this.apiUrl}/listPurchaseOrderLine`);
  }

  addPurchaseOrderLine(purchaseOrderLineDTO : PurchaseOrderLineDTO):Observable<PurchaseOrderLine>{
    return this.http.post<PurchaseOrderLine>(`${this.apiUrl}/addPurchaseOrderLine`,purchaseOrderLineDTO)
  }

  findPurchaseOrderLineById(id:number):Observable<PurchaseOrderLine>{
    return this.http.get<PurchaseOrderLine>(`${this.apiUrl}/find/${id}`);
  }

  editPurchaseOrderLine(purchaseOrderLineDTO:PurchaseOrderLineDTO, purchaseOrderLineId:number):Observable<PurchaseOrderLine>{
    return this.http.put<PurchaseOrderLine>(`${this.apiUrl}/update/${purchaseOrderLineId}`,purchaseOrderLineDTO);
  }

  deletePurchaseOrderLine(purchaseOrderLineId:number):Observable<string>{
    return this.http.delete<string>(`${this.apiUrl}/delete/${purchaseOrderLineId}`);
  }

  //addPurchaseOrderLineList
  //PurchaseOrderLineCreateRequest
  addpurchaseOrderLineList(purchaseOrderLineRq : PurchaseOrderLineRequest[]):Observable<{ message: string }>{
    return this.http.post<{ message: string }>(`${this.apiUrl}/addPurchaseOrderLineList`,purchaseOrderLineRq)
  }

}
