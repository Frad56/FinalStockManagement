package com.example.store.model.stockManagement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;


  //  @Size(max=100)
    @Column(name ="reference")
    private String reference;

    @Column(name ="designation")
    private String designation;

    @Column(name ="brand")
    private String brand;


    @Column(name ="description")
    private String description;


    @Column(name ="base_price",precision = 10, scale = 2)
    private BigDecimal basePrice;


    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="aisle_id")
    private Aisle aisle;

   // @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    //@JsonManagedReference
    //private List<ProductSupplier> product_suppliers = new ArrayList<>();
//   @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
//   private List<ProductVariant> variants;



}
