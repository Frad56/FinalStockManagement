package com.example.store.Model.StockMangement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="aisle")
public class Aisle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aisleId;

    @Column(name= "name")
    private String name;

}
