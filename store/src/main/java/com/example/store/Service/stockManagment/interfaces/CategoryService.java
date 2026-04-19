package com.example.store.Service.stockManagment.interfaces;


import com.example.store.DTO.stockManagment.CategoryDTO;
import com.example.store.Model.StockMangement.Category;
import com.example.store.Model.StockMangement.CharacteristicTypeValue;

import java.util.List;

public interface CategoryService {

    Category saveCategory(CategoryDTO category);

    Category findCategoryById(Long categoryId);

    List<Category> fetchCategoryList();

    Category updateCategory(CategoryDTO category, Long categoryId);

    void deleteCategoryById(Long categoryId);

    boolean validateValue(String value, CharacteristicTypeValue typeValue);

   // Object convertValue(String value,CharacteristicTypeValue typeValue);

    List<Category> leafCategoryList();

    List<Category> findCategoriesWithoutProducts();
}
