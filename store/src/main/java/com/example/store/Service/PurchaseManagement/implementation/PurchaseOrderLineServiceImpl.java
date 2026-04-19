package com.example.store.Service.PurchaseManagement.implementation;


import com.example.store.DTO.PurchaseManagement.PurchaseOrderLineDTO;

import com.example.store.DTO.PurchaseManagement.request.PurchaseOrderLineRequest;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.PurchaseManagement.PurchaseOrder;
import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import com.example.store.Model.StockMangement.ProductVariant;

import com.example.store.Repository.PurchaseManagement.PurchaseOrderLineRepository;
import com.example.store.Service.PurchaseManagement.interfaces.PurchaseOrderLineService;
import com.example.store.Service.PurchaseManagement.interfaces.PurchaseOrderService;
import com.example.store.Service.stockManagment.interfaces.ProductVariantService;
import com.example.store.Service.stockManagment.interfaces.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderLineServiceImpl  implements PurchaseOrderLineService {


    private final PurchaseOrderLineRepository  purchaseOrderLineRepository;
    private final PurchaseOrderService purchaseOrderService;
    private final ProductVariantService productVariantService;
    private final UnitService unitService;

    public PurchaseOrderLineServiceImpl(PurchaseOrderLineRepository  purchaseOrderLineRepository,
                                        PurchaseOrderService purchaseOrderService,
                                        ProductVariantService productVariantService,
                                        UnitService unitService){
        this.purchaseOrderLineRepository=purchaseOrderLineRepository;
        this.purchaseOrderService=purchaseOrderService;
        this.productVariantService=productVariantService;
        this.unitService=unitService;
    }

    private void mapDTOToPurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLineDTO, PurchaseOrderLine purchaseOrderLine){
        ProductVariant pv = productVariantService.findProductVariantById(purchaseOrderLineDTO.getProductVariantId());
        purchaseOrderLine.setProductVariant(pv);
        PurchaseOrder purchaseOrder =purchaseOrderService.findPurchaseOrderById(purchaseOrderLineDTO.getPurchaseOrderId());
        purchaseOrderLine.setPurchaseOrder(purchaseOrder);
        //Unit unit= unitService.findUnitById(purchaseOrderLineDTO.getUnitId());

        purchaseOrderLine.setQuantity(purchaseOrderLineDTO.getQuantity());
        purchaseOrderLine.setUnitPrice(purchaseOrderLineDTO.getUnitPrice());
        //purchaseOrderLine.setDiscount(purchaseOrderLineDTO.getDiscount());
    }

    @Override
    public PurchaseOrderLine savePurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderDTO){
        PurchaseOrderLine purchaseOrder = new PurchaseOrderLine();
        mapDTOToPurchaseOrderLine(purchaseOrderDTO,purchaseOrder);
        return purchaseOrderLineRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrderLine findPurchaseOrderLineById(Long purchaseOrderLineId){
        return purchaseOrderLineRepository.findById(purchaseOrderLineId).orElseThrow(() ->
                new ElementNotFoundException(purchaseOrderLineId));
    }

    @Override
    public List<PurchaseOrderLine> fetchPurchaseOrderLineList(){
        return purchaseOrderLineRepository.findAll();
    }

    @Override
    public PurchaseOrderLine updatePurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLineDTO, Long purchaseOrderLineId){
        PurchaseOrderLine purchaseOrderLine = findPurchaseOrderLineById(purchaseOrderLineId);
        mapDTOToPurchaseOrderLine(purchaseOrderLineDTO,purchaseOrderLine);
        return purchaseOrderLineRepository.save(purchaseOrderLine);
    }
    @Override
    public void deletePurchaseOrderLineById(Long purchaseOrderLineId){
        findPurchaseOrderLineById(purchaseOrderLineId);
        purchaseOrderLineRepository.deleteById(purchaseOrderLineId);
    }

    @Override
    public void saveListOfPurchaseOrder(List<PurchaseOrderLineRequest> purchaseOrderLineCreateRequests){

        for(PurchaseOrderLineRequest purchaseOrderRequest:purchaseOrderLineCreateRequests){
            ProductVariant pv=productVariantService.findProductVariantById(purchaseOrderRequest.getProductVariantId());
            PurchaseOrder purchaseOrder= purchaseOrderService.findPurchaseOrderById(purchaseOrderRequest.getPurchaseOrderId());


            PurchaseOrderLine purchaseOrderLine =new PurchaseOrderLine();

            purchaseOrderLine.setUnitPrice(purchaseOrderRequest.getUnitPrice());
            purchaseOrderLine.setQuantity(purchaseOrderRequest.getQuantity());



            purchaseOrderLine.setProductVariant(pv);
            purchaseOrderLine.setPurchaseOrder(purchaseOrder);


            purchaseOrderLineRepository.save(purchaseOrderLine);
        }
       // List<PurchaseOrderLineListRequest> purchaseOrderLineCreateRequests, Long productVariantId, Long purchaseOrderId
//         ProductVariant pv=productVariantService.findProductVariantById(purchaseOrderLineCreateRequests.getProductVariantId());
//         PurchaseOrder purchaseOrder= purchaseOrderService.findPurchaseOrderById(purchaseOrderLineCreateRequests.getPurchaseOrderId());
//
//        for(PurchaseOrderLineListRequest purchaseOrderLineCreateRequest:purchaseOrderLineCreateRequests.getPurchaseOrderLineListRequest()){
//            PurchaseOrderLine purchaseOrderLine =new PurchaseOrderLine();
//            purchaseOrderLine.setUnitPrice(purchaseOrderLineCreateRequest.getUnitPrice());
//            purchaseOrderLine.setQuantity(purchaseOrderLineCreateRequest.getQuantity());
//            purchaseOrderLine.setProductVariant(pv);
//            purchaseOrderLine.setPurchaseOrder(purchaseOrder);
//        }
//        return purchaseOrder.getPurchaseOrderId();






    }

}
