package com.example.store.model.salesManagement;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@Entity
@Table(name= "installment")
public class Installment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long installmentId;

    @ManyToOne
    @JoinColumn(name = "sales_order_id", nullable = false)
    private SalesOrder salesOrder;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(updatable = false, name = "due_date")
    private LocalDate dueDate;

    private boolean paid;
}
