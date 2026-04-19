package com.example.store.Service.stockManagment.interfaces;

import com.example.store.DTO.stockManagment.UnitDTO;
import com.example.store.Model.StockMangement.Unit;

import java.util.List;

public interface UnitService {
    Unit saveUnit(UnitDTO unit);
    Unit findUnitById(Long unitId);
    Unit updateUnit(UnitDTO unit, Long unitId);
    void deleteUnitById(Long unitId);
    List<Unit> fetchUnitList();
}
