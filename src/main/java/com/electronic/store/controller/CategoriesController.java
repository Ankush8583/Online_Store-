package com.electronic.store.controller;


import com.electronic.store.dtos.ApiResponseMessage;
import com.electronic.store.dtos.CategoriesDto;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.ProductDto;
import com.electronic.store.services.CategoriesService;
import com.electronic.store.services.Impl.CtegoriesImpl;
import com.electronic.store.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

//create

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private ProductService productService;


    @PostMapping
    public ResponseEntity<CategoriesDto> createCategories(@Valid @RequestBody CategoriesDto categoriesDto) {

        CategoriesDto categoriesDto1 = categoriesService.create(categoriesDto);
        return new ResponseEntity<CategoriesDto>(categoriesDto1, HttpStatus.CREATED);
    }


    //update


    @PutMapping("/{categoriesId}")
    public ResponseEntity<CategoriesDto> updateCategories(@Valid @PathVariable("categoriesId") String categoriesId, @RequestBody CategoriesDto categoriesDto) {


        CategoriesDto upadtedCategoriesDto = categoriesService.update(categoriesDto, categoriesId);
        return new ResponseEntity<>(upadtedCategoriesDto, HttpStatus.OK);
    }


    //delete

    @DeleteMapping("/{categoriesId}")
    public ResponseEntity<ApiResponseMessage> deleteCategories(@PathVariable String categoriesId) {

        categoriesService.delete(categoriesId);
        ApiResponseMessage message = ApiResponseMessage.builder().message("Categories deleted successfully !!").status(HttpStatus.OK).success(true).build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    //getAll

    @GetMapping
    public ResponseEntity<PageableResponse<CategoriesDto>> getAllCategories(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                                            @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize) {


        PageableResponse categories = categoriesService.getAll(pageNumber, pageSize);
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }


    //getSingle


//create product with categories

    @PostMapping("/{categoriesId}/products")
    public ResponseEntity<ProductDto> createProductWithCategories(@PathVariable("categoriesId") String categoriesId, @RequestBody ProductDto productDto) {


        ProductDto productWithCategories = productService.createWithCategories(productDto, categoriesId);
        return new ResponseEntity<>(productWithCategories, HttpStatus.CREATED);
    }



    //update categories of product

    @PutMapping("/{productId}/products/{categoriesId}")
    public ResponseEntity<ProductDto> updateCategories(@PathVariable String productId, @PathVariable String categoriesId) {

        ProductDto productDto = productService.updateCategories(productId, categoriesId);

        return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
    }


    //get Products of Categories

    @GetMapping("/{categoriesId}/products")
    public ResponseEntity<PageableResponse <ProductDto>> getProductOfCategories(@PathVariable String categoriesId,@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                                                @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize){

      PageableResponse<ProductDto> response=  productService.getAllCategories(categoriesId,pageNumber,pageSize);

       return new ResponseEntity<>(response,HttpStatus.OK);
    }



}


