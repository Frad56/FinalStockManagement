package com.example.store.service.stockManagment.implementation;


import com.example.store.dto.stockManagment.CategoryDTO;
import com.example.store.dto.stockManagment.CategoryDTOTest;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.exception.ResourceInUseException;
import com.example.store.model.stockManagement.Category;
import com.example.store.model.stockManagement.CharacteristicTypeValue;
import com.example.store.repository.stockManagement.CategoryRepository;
import com.example.store.service.stockManagment.interfaces.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


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


    private CategoryDTOTest toTreeDTO(Category category) {
        CategoryDTOTest dto = new CategoryDTOTest();
        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setParentId(category.getParent() != null ? category.getParent().getCategoryId() : null);
        dto.setChildren(new ArrayList<>());
        return dto;
    }
    @Override
    //@Transactional
    public List<CategoryDTOTest> findAllWithChildren() {
        List<Category> categories = categoryRepository.findAll();

        Map<Long, CategoryDTOTest> map = new LinkedHashMap<>();
        for (Category category : categories) {
            map.put(category.getCategoryId(), toTreeDTO(category));
        }

        List<CategoryDTOTest> roots = new ArrayList<>();

        for (Category category : categories) {
            CategoryDTOTest current = map.get(category.getCategoryId());

            if (category.getParent() == null) {
                roots.add(current);
            } else {
                CategoryDTOTest parent = map.get(category.getParent().getCategoryId());
                if (parent != null) {
                    parent.getChildren().add(current);
                }
            }
        }

        sortTree(roots);
        return roots;
    }





    private CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setParentId(
                category.getParent() != null ? category.getParent().getCategoryId() : null
        );
        dto.setChildrenIds(
                category.getChildren() == null ? new ArrayList<>() :
                        category.getChildren().stream()
                                .map(Category::getCategoryId)
                                .collect(Collectors.toList())
        );
        return dto;
    }
    public void sortTree(List<CategoryDTOTest> nodes) {
        nodes.sort(Comparator.comparing(
                CategoryDTOTest::getName,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
        ));

        for (CategoryDTOTest node : nodes) {
            if (node.getChildren() != null) {
                sortTree(node.getChildren());
            }
        }
    }


    @Override
    public boolean hasProductsInSubtree(Category category) {
        if (categoryRepository.existsProductByCategoryId(category.getCategoryId())) {
            return true;
        }
        if (category.getChildren() != null) {
            for (Category child : category.getChildren()) {
                if (hasProductsInSubtree(child)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void deleteCategoryById(Long categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ElementNotFoundException(categoryId));

        if (hasProductsInSubtree(category)) {
            throw new ResourceInUseException(
                    "Cannot delete category '" + category.getName() +
                            "': it or one of its subcategories has linked products."
            );
        }
        categoryRepository.deleteById(categoryId);
    }
}

