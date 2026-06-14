package com.example.store.repository.quotationManagement;


import com.example.store.model.quotationManagement.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository  extends JpaRepository<Quotation,Long> {
}
