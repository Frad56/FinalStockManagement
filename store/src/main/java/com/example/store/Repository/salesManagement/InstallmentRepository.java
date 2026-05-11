package com.example.store.Repository.salesManagement;


import com.example.store.Model.salesManagement.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment,Long> {
}
