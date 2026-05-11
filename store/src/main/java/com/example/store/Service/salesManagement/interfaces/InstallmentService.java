package com.example.store.Service.salesManagement.interfaces;

import com.example.store.DTO.salesManagement.InstallmentDTO;
import com.example.store.Model.salesManagement.Installment;
import com.example.store.Model.salesManagement.SalesOrder;

import java.util.List;

public interface InstallmentService {

    Installment saveInstallment(InstallmentDTO installmentDTO);
    List<Installment> fetchInstallmentList();
    Installment findInstallmentById(Long installmentId);
    Installment updateInstallment(InstallmentDTO installmentDTO, Long installmentId);
    void deleteInstallmentById(Long installmentId);

}
