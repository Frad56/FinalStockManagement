package com.example.store.service.salesManagement.implementation;

import com.example.store.dto.salesManagement.InstallmentDTO;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.model.salesManagement.Installment;
import com.example.store.model.salesManagement.SalesOrder;
import com.example.store.repository.salesManagement.InstallmentRepository;
import com.example.store.service.salesManagement.interfaces.InstallmentService;
import com.example.store.service.salesManagement.interfaces.SalesOrderService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InstallmentServiceImpl implements InstallmentService {

    private final InstallmentRepository installmentRepository;
    private final SalesOrderService salesOrderService;
    public InstallmentServiceImpl(InstallmentRepository installmentRepository,
                                  SalesOrderService salesOrderService){
        this.installmentRepository=installmentRepository;
        this.salesOrderService=salesOrderService;
    }

    private void mapDTOToInstallment(InstallmentDTO installmentDTO,Installment installment){
        SalesOrder salesOrder = salesOrderService.findSalesOrderById(installmentDTO.getSalesOrderId());
        installment.setSalesOrder(salesOrder);
        installment.setAmount(installmentDTO.getAmount());
        installment.setPaid(installmentDTO.isPaid());
    }

    @Override
    public Installment saveInstallment(InstallmentDTO installmentDTO){
        Installment installment = new Installment();
        mapDTOToInstallment(installmentDTO,installment);
        return installmentRepository.save(installment);
    }

    @Override
    public List<Installment> fetchInstallmentList(){
        return installmentRepository.findAll();
    }

    @Override
    public Installment findInstallmentById(Long installmentId){
        return installmentRepository.findById(installmentId).orElseThrow(()->new ElementNotFoundException(installmentId));
    }

    @Override
    public Installment updateInstallment(InstallmentDTO installmentDTO, Long installmentId){
        Installment installment = findInstallmentById(installmentId);
        mapDTOToInstallment(installmentDTO,installment);
        return installmentRepository.save(installment);
    }

    @Override
    public void deleteInstallmentById(Long installmentId){
        Installment installment = findInstallmentById(installmentId);
        installmentRepository.deleteById(installmentId);
    }
}
