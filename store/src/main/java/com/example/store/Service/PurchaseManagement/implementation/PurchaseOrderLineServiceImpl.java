package com.example.store.Service.PurchaseManagement.implementation;


import com.example.store.DTO.PurchaseManagement.PurchaseOrderDTO;
import com.example.store.DTO.PurchaseManagement.PurchaseOrderLineDTO;

import com.example.store.DTO.stockManagment.MovementInStockDTO;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.PurchaseManagement.PurchaseOrder;
import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import com.example.store.Model.StockMangement.ProductVariant;

import com.example.store.Model.StockMangement.Unit;
import com.example.store.Repository.PurchaseManagement.PurchaseOrderLineRepository;
import com.example.store.Service.PurchaseManagement.interfaces.PurchaseOrderLineService;
import com.example.store.Service.PurchaseManagement.interfaces.PurchaseOrderService;
import com.example.store.Service.stockManagment.interfaces.MovementInStockService;
import com.example.store.Service.stockManagment.interfaces.ProductVariantService;
import com.example.store.Service.stockManagment.interfaces.UnitService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import static com.example.store.Model.StockMangement.MovementInStockType.ENTRY;

@Service
public class PurchaseOrderLineServiceImpl  implements PurchaseOrderLineService {


    private final PurchaseOrderLineRepository  purchaseOrderLineRepository;
    private final PurchaseOrderService purchaseOrderService;
    private final ProductVariantService productVariantService;
    private final UnitService unitService;
    private final MovementInStockService movementInStockService;

    public PurchaseOrderLineServiceImpl(PurchaseOrderLineRepository  purchaseOrderLineRepository,
                                        PurchaseOrderService purchaseOrderService,
                                        ProductVariantService productVariantService,
                                        UnitService unitService,
                                        MovementInStockService movementInStockService){
        this.purchaseOrderLineRepository=purchaseOrderLineRepository;
        this.purchaseOrderService=purchaseOrderService;
        this.productVariantService=productVariantService;
        this.unitService=unitService;
        this.movementInStockService=movementInStockService;
    }


    public void calculateTotalIfDiscountWithPercentage(String percentage,PurchaseOrderLineDTO lineDTO,PurchaseOrderLine line){

        if (percentage == null || percentage.trim().isEmpty()) {
            line.setDiscount(BigDecimal.ZERO);
            line.setTotal(line.getTotalTTC());
            return;
        }

        String discountPercentage = percentage.replace("%","").trim();

        BigDecimal value= new BigDecimal(discountPercentage);
        value = value.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

        BigDecimal discount =line.getTotalTTC().multiply(value);
        line.setDiscount(discount);
        line.setTotal(line.getTotalTTC().subtract(discount));

    }
    public void calculateTotalIfDiscountWithoutPercentage(PurchaseOrderLineDTO lineDTO,PurchaseOrderLine line){
        if (lineDTO.getDiscount() == null || lineDTO.getDiscount().trim().isEmpty()) {
            line.setDiscount(BigDecimal.ZERO);
            line.setTotal(line.getTotalTTC());
            return;
        }
        BigDecimal number = new BigDecimal(lineDTO.getDiscount().toString());

        line.setDiscount(number);
        BigDecimal total = line.getTotalTTC().subtract(number);
        line.setTotal(total);


    }


    private void mapDTOToPurchaseOrderLine(PurchaseOrderLineDTO purchaseOrderLineDTO, PurchaseOrderLine purchaseOrderLine) {

        ProductVariant pv = productVariantService.findProductVariantById(purchaseOrderLineDTO.getProductVariantId());
        System.out.println("pv.getProductVariantId() = " + pv.getProductVariantId());
        purchaseOrderLine.setProductVariant(pv);
        PurchaseOrder purchaseOrder = purchaseOrderService.findPurchaseOrderById(purchaseOrderLineDTO.getPurchaseOrderId());
        System.out.println("purchaseOrder.getPurchaseOrderId() = " + purchaseOrder.getPurchaseOrderId());
        purchaseOrderLine.setPurchaseOrder(purchaseOrder);





        if (purchaseOrderLineDTO.getUnitId() != null) {
            Unit unit = unitService.findUnitById(purchaseOrderLineDTO.getUnitId());
            purchaseOrderLine.setUnit(unit);
        } else {
            purchaseOrderLine.setUnit(null);
        }
        if (purchaseOrderLineDTO.getQuantity() == null) {
            throw new IllegalArgumentException("Quantity is required");
        }

        purchaseOrderLine.setQuantity(purchaseOrderLineDTO.getQuantity());



        if (purchaseOrderLineDTO.getUnitPriceHt() != null && purchaseOrderLineDTO.getUnitPriceTTC() != null) {
            throw new IllegalArgumentException("Choose either HT or TTC, not both");
        }
        if (purchaseOrderLineDTO.getUnitPriceHt() == null && purchaseOrderLineDTO.getUnitPriceTTC() == null) {
            throw new IllegalArgumentException("Price is required");
        }
        if (purchaseOrderLineDTO.getTax() == null) {
            throw new IllegalArgumentException("Tax is required");
        }


        BigDecimal tax = purchaseOrderLineDTO.getTax().divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP);
        BigDecimal multiplier = BigDecimal.ONE.add(tax);
        purchaseOrderLine.setTax(purchaseOrderLineDTO.getTax());
        if (purchaseOrderLineDTO.getUnitPriceHt() != null) {
            purchaseOrderLine.setUnitPriceHt(purchaseOrderLineDTO.getUnitPriceHt());
            BigDecimal totalHt = purchaseOrderLineDTO.getUnitPriceHt().multiply(purchaseOrderLineDTO.getQuantity());
            purchaseOrderLine.setTotalHT(totalHt);

            BigDecimal totalTTC = totalHt.multiply(multiplier);
            purchaseOrderLine.setTotalTTC(totalTTC);
            BigDecimal unitPriceTTC = purchaseOrderLineDTO.getUnitPriceHt().multiply(multiplier);
            purchaseOrderLine.setUnitPriceTTC(unitPriceTTC);


        } else if (purchaseOrderLineDTO.getUnitPriceTTC() != null) {

            BigDecimal unitPriceTTC = purchaseOrderLineDTO.getUnitPriceTTC();
            purchaseOrderLine.setUnitPriceTTC(unitPriceTTC);

            BigDecimal unitPriceHt = unitPriceTTC.divide(multiplier, 5, RoundingMode.HALF_UP);
            purchaseOrderLine.setUnitPriceHt(unitPriceHt);

            BigDecimal totalTTC = unitPriceTTC.multiply(purchaseOrderLineDTO.getQuantity());
            purchaseOrderLine.setTotalTTC(totalTTC);
            BigDecimal totalHt = unitPriceHt.multiply(purchaseOrderLineDTO.getQuantity());
            purchaseOrderLine.setTotalHT(totalHt);
        }
    }




    @Override
    public PurchaseOrderLine savePurchaseOrderLineWithPercentage(PurchaseOrderLineDTO purchaseOrderDTO){
        PurchaseOrderLine purchaseOrder = new PurchaseOrderLine();
        mapDTOToPurchaseOrderLine(purchaseOrderDTO,purchaseOrder);
        //purchaseOrderLineRepository.save(purchaseOrder);
        calculateTotalIfDiscountWithPercentage(purchaseOrderDTO.getDiscount(),purchaseOrderDTO,purchaseOrder);
        return purchaseOrderLineRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrderLine savePurchaseOrderLineWithoutPercentage(PurchaseOrderLineDTO purchaseOrderDTO){
        PurchaseOrderLine purchaseOrder = new PurchaseOrderLine();
        mapDTOToPurchaseOrderLine(purchaseOrderDTO,purchaseOrder);
        //purchaseOrderLineRepository.save(purchaseOrder);

        calculateTotalIfDiscountWithoutPercentage(purchaseOrderDTO,purchaseOrder);
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
        if(purchaseOrderLineDTO.getDiscount() != null && !purchaseOrderLineDTO.getDiscount().trim().isEmpty()){
            calculateTotalIfDiscountWithoutPercentage(purchaseOrderLineDTO,purchaseOrderLine);
        }
        return purchaseOrderLineRepository.save(purchaseOrderLine);
    }

    @Override
    public void deletePurchaseOrderLineById(Long purchaseOrderLineId){
       PurchaseOrderLine purchaseOrderLine= findPurchaseOrderLineById(purchaseOrderLineId);
       PurchaseOrder purchaseOrder = purchaseOrderLine.getPurchaseOrder();
       BigDecimal totalAmountPurchaseOrder = purchaseOrder.getTotalAmount();
        BigDecimal totalPurchaseOrderLine = purchaseOrderLine.getTotal();
         if (totalAmountPurchaseOrder != null || totalPurchaseOrderLine != null) {
                BigDecimal newTotalAmount = totalAmountPurchaseOrder.subtract(totalPurchaseOrderLine);
                purchaseOrder.setTotalAmount(newTotalAmount);
                purchaseOrderService.updatePurchaseOrderTotalAmount(purchaseOrder);
         }
        purchaseOrderLineRepository.deleteById(purchaseOrderLineId);
    }

    @Override
    public void saveListOfPurchaseOrderIfDiscountWithoutPercentage(List<PurchaseOrderLineDTO> purchaseOrderLineCreateDTO){

        for(PurchaseOrderLineDTO purchaseOrderRequest:purchaseOrderLineCreateDTO){
            ProductVariant pv=productVariantService.findProductVariantById(purchaseOrderRequest.getProductVariantId());
            PurchaseOrder purchaseOrder= purchaseOrderService.findPurchaseOrderById(purchaseOrderRequest.getPurchaseOrderId());


            PurchaseOrderLine purchaseOrderLine =new PurchaseOrderLine();
            mapDTOToPurchaseOrderLine(purchaseOrderRequest,purchaseOrderLine);
            calculateTotalIfDiscountWithoutPercentage(purchaseOrderRequest,purchaseOrderLine);

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

    @Override
    public void saveListOfPurchaseOrderIfDiscountWithPercentage(List<PurchaseOrderLineDTO> purchaseOrderLineCreateDTO) {

        for (PurchaseOrderLineDTO purchaseOrderRequest : purchaseOrderLineCreateDTO) {
            ProductVariant pv = productVariantService.findProductVariantById(purchaseOrderRequest.getProductVariantId());
            PurchaseOrder purchaseOrder = purchaseOrderService.findPurchaseOrderById(purchaseOrderRequest.getPurchaseOrderId());

            PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine();
            mapDTOToPurchaseOrderLine(purchaseOrderRequest, purchaseOrderLine);
            calculateTotalIfDiscountWithoutPercentage(purchaseOrderRequest, purchaseOrderLine);


            purchaseOrderLineRepository.save(purchaseOrderLine);
        }
    }


    @Override
    public void totalAmountOfPurchaseOrder(Long purchaseOrderId){
        PurchaseOrder purchaseOrder = purchaseOrderService.findPurchaseOrderById(purchaseOrderId);
        List<PurchaseOrderLine> purchaseOrderLines = purchaseOrderLineRepository.findByPurchaseOrder_PurchaseOrderId(purchaseOrderId);
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (PurchaseOrderLine line : purchaseOrderLines) {
            System.out.println("line.getTotal() = " + line.getTotal());
            totalAmount=totalAmount.add(line.getTotal());
            System.out.println("totalAmount = " + totalAmount);
        }
        purchaseOrder.setTotalAmount(totalAmount);
        purchaseOrderService.updatePurchaseOrderTotalAmount(purchaseOrder);
    }


    @Override
    @Transactional
    public void deleteByPurchaseOrder(Long purchaseOrderId) {
        purchaseOrderService.findPurchaseOrderById(purchaseOrderId);
        purchaseOrderLineRepository.deleteByPurchaseOrderId(purchaseOrderId);
    }


    @Override
    public List<PurchaseOrderLine> findByPurchaseOrderLineByPurchaseOrderId(Long purchaseOrderId)
    {
        PurchaseOrder purchaseOrder = purchaseOrderService.findPurchaseOrderById(purchaseOrderId);

//        List<PurchaseOrderLine> list = purchaseOrderLineRepository.findByPurchaseOrder_PurchaseOrderId(purchaseOrderId);
//
//        System.out.println("SIZE = " + list.size());
        return purchaseOrderLineRepository.findByPurchaseOrder_PurchaseOrderId(purchaseOrderId);
    }
}
