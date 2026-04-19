package com.example.store.DTO.stockManagment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {
    private Long categoryId;

    private String name;

    private String description;

    private Long parentId;

    private List<Long> childrenIds;
}
