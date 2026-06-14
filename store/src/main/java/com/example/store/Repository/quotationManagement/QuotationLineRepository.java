package com.example.store.repository.quotationManagement;



import com.example.store.model.quotationManagement.QuotationLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuotationLineRepository  extends JpaRepository<QuotationLine,Long> {

    List<QuotationLine> findByQuotation_QuotationId(Long quotationId);

}
