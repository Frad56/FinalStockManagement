package com.example.store.Service.PurchaseManagement.PricingService;


import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import com.example.store.Model.StockMangement.ProductVariant;
import com.example.store.Repository.PurchaseManagement.PurchaseOrderLineRepository;
import com.example.store.Service.stockManagment.interfaces.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class PricingServiceImpl {


    @Autowired
    private  final PurchaseOrderLineRepository purchaseOrderLineRepository;
    private final ProductVariantService productVariantService;
    public PricingServiceImpl(PurchaseOrderLineRepository purchaseOrderLineRepository, ProductVariantService productVariantService){
        this.purchaseOrderLineRepository=purchaseOrderLineRepository;
        this.productVariantService=productVariantService;
    }

    public BigDecimal getLastPurchasePrice(Long variantId) {
        ProductVariant pv = productVariantService.findProductVariantById(variantId);
        return purchaseOrderLineRepository
                .findTopByProductVariantOrderByPurchaseOrderLineIdDesc(pv)
                .map(PurchaseOrderLine::getUnitPriceTTC)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal getAveragePurchasePrice(Long variantId) {

        List<PurchaseOrderLine> lines =
                purchaseOrderLineRepository.findByProductVariant_ProductVariantId(variantId);

        if (lines.isEmpty()) return BigDecimal.ZERO;

        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalQuantity = BigDecimal.ZERO;

        for (PurchaseOrderLine l : lines) {
            totalAmount = totalAmount.add(
                    l.getUnitPriceHt().multiply(l.getQuantity())
            );
            totalQuantity = totalQuantity.add(l.getQuantity());
        }

        return totalAmount.divide(totalQuantity, 2, RoundingMode.HALF_UP);
    }
}
