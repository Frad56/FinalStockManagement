package com.example.store.service.salesManagement.interfaces;

import com.example.store.dto.salesManagement.InstallmentDTO;
import com.example.store.model.salesManagement.Installment;

import java.util.List;

public interface InstallmentService {

    Installment saveInstallment(InstallmentDTO installmentDTO);
    List<Installment> fetchInstallmentList();
    Installment findInstallmentById(Long installmentId);
    Installment updateInstallment(InstallmentDTO installmentDTO, Long installmentId);
    void deleteInstallmentById(Long installmentId);

}
