package com.example.store.Service.stockManagment.implementation;

import com.example.store.DTO.stockManagment.UnitDTO;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Exception.ResourceInUseException;
import com.example.store.Model.StockMangement.Unit;
import com.example.store.Repository.StockManagment.ProductUnitSaleRepository;
import com.example.store.Repository.StockManagment.UnitRepository;
import com.example.store.Service.stockManagment.interfaces.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final ProductUnitSaleRepository productUnitSaleRepository;

    public UnitServiceImpl(UnitRepository unitRepository, ProductUnitSaleRepository productUnitSaleRepository) {
        this.unitRepository = unitRepository;
        this.productUnitSaleRepository = productUnitSaleRepository;
    }

    private void mapUnitDTOToUnit(UnitDTO unitDTO, Unit unit) {
        unit.setName(unitDTO.getUnitName());
        unit.setSymbol(unitDTO.getUnitSymbol());
    }

    @Override
    public Unit findUnitById(Long unitId) {
        return unitRepository.findById(unitId).orElseThrow(()->
                new RuntimeException("Unit not found with id: " + unitId));
    }


    @Override
    public Unit saveUnit(UnitDTO unitDTO) {
        String newUnitName= unitDTO.getUnitName().trim().toLowerCase();
        String newUnitSymbol= unitDTO.getUnitSymbol().trim().toLowerCase();
        if(unitRepository.existsByName(newUnitName)) {
            throw new RuntimeException("Unit with name '" + unitDTO.getUnitName() + "' already exists.");
        }
        if(unitRepository.existsBySymbol(newUnitSymbol)) {
            throw new RuntimeException("Unit with symbol '" + unitDTO.getUnitSymbol() + "' already exists.");
        }
        Unit unit = new Unit();
        mapUnitDTOToUnit(unitDTO, unit);
        return unitRepository.save(unit);
    }

    @Override
    public Unit updateUnit(UnitDTO unitDTO, Long unitId) {
        Unit existingUnit = findUnitById(unitId);
        mapUnitDTOToUnit(unitDTO, existingUnit);
        return unitRepository.save(existingUnit);
    }

    @Override
    public void deleteUnitById(Long unitId) {
        if(!unitRepository.existsById(unitId)) {
            throw new ElementNotFoundException("Unit not found with id: " + unitId);
        }
        boolean isUnitInUse = productUnitSaleRepository.existsByUnit_UnitId(unitId);
        if(isUnitInUse) {
            throw new ResourceInUseException("This Unit is already used and cannot be deleted");
        }

        unitRepository.deleteById(unitId);
    }

    @Override
    public List<Unit> fetchUnitList() {
        return unitRepository.findAll();
    }

}
