package com.example.store.service.stockManagment.interfaces;


import com.example.store.dto.stockManagment.CategoryDTO;
import com.example.store.dto.stockManagment.CategoryDTOTest;
import com.example.store.model.stockManagement.Category;
import com.example.store.model.stockManagement.CharacteristicTypeValue;

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


    List<CategoryDTOTest> findAllWithChildren();


    void sortTree(List<CategoryDTOTest> nodes);
}
