package com.example.store.Model.StockMangement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="characteristic")

public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characteristicId;


    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private CharacteristicTypeValue type;


}
