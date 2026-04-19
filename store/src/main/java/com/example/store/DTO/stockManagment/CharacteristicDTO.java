package com.example.store.DTO.stockManagment;


import com.example.store.Model.StockMangement.CharacteristicTypeValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CharacteristicDTO {

    private String name;
    private CharacteristicTypeValue type;
}
