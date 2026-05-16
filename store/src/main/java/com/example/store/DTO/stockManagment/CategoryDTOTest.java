package com.example.store.DTO.stockManagment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTOTest {
    private Long categoryId;
    private String name;
    private String description;
    private Long parentId;
    private List<CategoryDTOTest> children = new ArrayList<>();
}