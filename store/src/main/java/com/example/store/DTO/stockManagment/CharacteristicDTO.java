package com.example.store.dto.stockManagment;


import com.example.store.model.stockManagement.CharacteristicTypeValue;
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
