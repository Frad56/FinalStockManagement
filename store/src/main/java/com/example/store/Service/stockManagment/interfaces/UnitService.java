package com.example.store.service.stockManagment.interfaces;

import com.example.store.dto.stockManagment.UnitDTO;
import com.example.store.model.stockManagement.Unit;

import java.util.List;

public interface UnitService {
    Unit saveUnit(UnitDTO unit);
    Unit findUnitById(Long unitId);
    Unit updateUnit(UnitDTO unit, Long unitId);
    void deleteUnitById(Long unitId);
    List<Unit> fetchUnitList();
}
