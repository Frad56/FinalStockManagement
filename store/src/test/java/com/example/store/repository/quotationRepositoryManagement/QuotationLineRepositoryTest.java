package com.example.store.repository.quotationRepositoryManagement;


import com.example.store.model.quotationManagement.Quotation;
import com.example.store.model.quotationManagement.QuotationLine;
import com.example.store.model.stockManagement.Aisle;
import com.example.store.model.stockManagement.Category;
import com.example.store.model.stockManagement.Product;
import com.example.store.model.stockManagement.ProductVariant;
import com.example.store.repository.quotationManagement.QuotationLineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@TestPropertySource(properties = {
        "spring.liquibase.enabled=false"
})

public class QuotationLineRepositoryTest {

    @Autowired
    private QuotationLineRepository quotationLineRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Quotation quotation1;
    private Quotation quotation2;


    @BeforeEach
    void setUp() {
        quotation1 = new Quotation();
        entityManager.persistAndFlush(quotation1);

        quotation2 = new Quotation();
        entityManager.persistAndFlush(quotation2);



        Category category = new Category();
        category.setName("cat_1");
        entityManager.persistAndFlush(category);

        Aisle aisle = new Aisle();
        aisle.setName("A001");
        entityManager.persistAndFlush(aisle);

        Product product = new Product();
        product.setAisle(aisle);
        product.setCategory(category);
        product.setReference("Ref01");
        entityManager.persistAndFlush(product);

        ProductVariant pv = new ProductVariant();
        pv.setCode("12367");
        pv.setSpecificPrice(BigDecimal.valueOf(50));
        pv.setQuantityInStock(3);
        pv.setProduct(product);
        entityManager.persistAndFlush(pv);


        ProductVariant pv_2 = new ProductVariant();
        pv_2.setCode("12345");
        pv_2.setSpecificPrice(BigDecimal.valueOf(50));
        pv_2.setQuantityInStock(3);
        pv_2.setProduct(product);
        entityManager.persistAndFlush(pv_2);




        QuotationLine line1 = new QuotationLine();
        line1.setQuotation(quotation1);
        line1.setProductVariant(pv);
        line1.setQuantity(BigDecimal.valueOf(2));
        line1.setUnitPrice(BigDecimal.valueOf(50));
        line1.setDiscount(BigDecimal.valueOf(10));


        entityManager.persistAndFlush(line1);

        QuotationLine line2 = new QuotationLine();
        line2.setQuotation(quotation2);
        line2.setProductVariant(pv_2);
        line2.setQuantity(BigDecimal.valueOf(2));
        line2.setUnitPrice(BigDecimal.valueOf(10));
        line2.setDiscount(BigDecimal.valueOf(5));

        entityManager.persistAndFlush(line2);


    }

    @Test
    void shouldFindLinesByQuotationId() {
        List<QuotationLine> lines = quotationLineRepository.findByQuotation_QuotationId(quotation1.getQuotationId());

        assertThat(lines).isNotEmpty();
        assertThat(lines).hasSize(1);
        lines.forEach(line ->
                assertThat(line.getQuotation().getQuotationId()).isEqualTo(quotation1.getQuotationId())
        );
    }
}
