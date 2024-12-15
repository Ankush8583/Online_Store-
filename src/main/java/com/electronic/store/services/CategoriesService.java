package com.electronic.store.services;

import com.electronic.store.dtos.CategoriesDto;
import com.electronic.store.dtos.PageableResponse;

public interface CategoriesService {


    // create category

    CategoriesDto create(CategoriesDto categoriesDto);


    //update category

    CategoriesDto update(CategoriesDto categoriesDto, String categoriesId);

    //Delete

    void delete(String categoriesId);


    //getAll category

    PageableResponse<CategoriesDto> getAll(int pageNumber, int pageSize);

    //get single category detail

    CategoriesDto get(String categoriesId);
}
