package com.electronic.store.services;

import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.ProductDto;

import java.util.List;

public interface ProductService {


    //create

    ProductDto create(ProductDto productDto);






    //update

    ProductDto update(ProductDto productDto, String productId);


    //delete

     void delete(String productId);


    //get single

     ProductDto get(String productId);


    //get all


 //PageableResponse<ProductDto> getAll();

    //get all live

    PageableResponse<ProductDto> getAll(int pageNumber, int pageSize);

    PageableResponse<ProductDto> getAllLive(int pageNumber, int pageSize);

    //search product

    PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber, int pageSize);

    //create product with categories

    ProductDto createWithCategories(ProductDto productDto,String categoriesId);


    ProductDto updateCategories(String productId, String categoriesId);

    PageableResponse<ProductDto> getAllCategories(String categoriesID,int pageNumber, int pageSize);

}
