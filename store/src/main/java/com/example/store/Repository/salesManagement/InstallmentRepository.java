package com.example.store.repository.salesManagement;


import com.example.store.model.salesManagement.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment,Long> {
}
