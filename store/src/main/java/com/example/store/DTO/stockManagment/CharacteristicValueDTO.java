package com.example.store.DTO.stockManagment;


import com.example.store.Model.StockMangement.ProductCharacteristic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CharacteristicValueDTO {

    private Long characteristicId;
    private Long productVariantId;
    private String value;

}
