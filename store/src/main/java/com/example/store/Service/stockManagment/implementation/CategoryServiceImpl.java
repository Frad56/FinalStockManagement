package com.example.store.Service.stockManagment.implementation;


import com.example.store.DTO.stockManagment.CategoryDTO;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.StockMangement.Category;
import com.example.store.Model.StockMangement.CharacteristicTypeValue;
import com.example.store.Repository.StockManagment.CategoryRepository;
import com.example.store.Service.stockManagment.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {


    private  final CategoryRepository categoryRepository;


    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository =categoryRepository;
    }



    private void mapDTOToCategory(CategoryDTO categoryDTO, Category category) {
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        if(categoryDTO.getParentId() != null){
            Category parent  =categoryRepository.findById(categoryDTO.getParentId())
                    .orElseThrow(()-> new ElementNotFoundException( "Parent category not found with id: "
                            + categoryDTO.getParentId()));
            category.setParent(parent);
        }
    }
    @Override
    public Category saveCategory(CategoryDTO categoryDTO){
        Category category = new Category();

        mapDTOToCategory(categoryDTO,category);
        return categoryRepository.save(category);
    }


    @Override
    public List<Category> fetchCategoryList(){
        return categoryRepository.findAll();
    }



    @Override
    public void deleteCategoryById(Long categoryId){
        if(!categoryRepository.existsById(categoryId)){
            throw new ElementNotFoundException(categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category findCategoryById(Long categoryId){
        return  categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ElementNotFoundException(categoryId));
    }

    @Override
    public Category updateCategory(CategoryDTO categoryDTO, Long categoryId){
        Category category =findCategoryById(categoryId);
        mapDTOToCategory(categoryDTO,category);
        return categoryRepository.save(category);
    }



    @Override
    public boolean validateValue(String value, CharacteristicTypeValue typeValue){

        switch (typeValue) {
            case STRING:
                return value != null;
            case DECIMAL:
                try {
                    Double.parseDouble(value);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            case INTEGER:
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    return false;
                }
            case BOOLEAN:
                return "true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value);
            case DATE:
                try{
                    LocalDate.parse(value);
                    return true;
                }catch (DateTimeException e) {
                    return false;
                }
        }
        return false;
    }

//    @Override
//    public Object convertValue(String value,CharacteristicTypeValue typeValue){
//        switch (typeValue){
//            case STRING :
//                return value;
//            case DECIMAL :
//                return Double.parseDouble(value);
//            case INTEGER:
//                return Integer.parseInt(value);
//            case BOOLEAN:
//                return Boolean.parseBoolean(value);
//            case DATE:
//                return LocalDate.parse(value);
//        }
//        return null;
//    }

    @Override
    public List<Category> leafCategoryList(){
        return categoryRepository.findLeafCategories();
    }

    @Override
    public List<Category> findCategoriesWithoutProducts(){
        return  categoryRepository.findCategoriesWithoutProducts();
    }
}

