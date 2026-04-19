package com.example.store.Model.StockMangement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "characteristic_value")
public class CharacteristicValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characteristicValueId;

    @ManyToOne
    @JoinColumn(name = "characteristic_id",nullable = false)
    private Characteristic characteristic;

    @ManyToOne
    @JoinColumn(name = "product_variant_id",nullable = false)
    private ProductVariant productVariant;

    @Column(name ="value")
    private String value;

}
