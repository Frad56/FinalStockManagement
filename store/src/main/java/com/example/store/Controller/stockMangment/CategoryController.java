package com.example.store.Controller.stockMangment;


import com.example.store.DTO.stockManagment.CategoryDTO;
import com.example.store.Model.StockMangement.Category;
import com.example.store.Service.stockManagment.interfaces.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private  final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @PostMapping("/addCategory")
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody CategoryDTO category) {
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    @GetMapping("/ListCategories")
    public ResponseEntity< List<Category> >fetchCategoryList(){
        List<Category> categories =categoryService.fetchCategoryList();;
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/leafCategoryList")
    public ResponseEntity<List<Category>> leafCategoryList(){
        return ResponseEntity.ok(categoryService.leafCategoryList());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable("id") Long categoryId
    ){
        Category category =  categoryService.findCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                   @PathVariable("id") Long categoryId){
        Category updatedCategory = categoryService.updateCategory(categoryDTO, categoryId);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoryByID(@PathVariable("id") Long categoryId){
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @GetMapping("/findCategoriesWithoutProducts")
    public ResponseEntity<List<Category>> findCategoriesWithoutProducts(){
        return ResponseEntity.ok(categoryService.findCategoriesWithoutProducts());
    }


}
