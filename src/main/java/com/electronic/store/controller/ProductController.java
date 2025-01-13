package com.electronic.store.controller;

import com.electronic.store.dtos.ApiResponseMessage;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.ProductDto;
import com.electronic.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //create

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){

        productDto.setAddedDate(LocalDateTime.now());
        return new ResponseEntity<>(productService.create(productDto), HttpStatus.CREATED);
    }





    //update
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId,@RequestBody ProductDto productDto){

        return new ResponseEntity<>(productService.update(productDto,productId), HttpStatus.OK);
    }


    //delete

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseMessage> deleteProduct(@PathVariable String productId){

        productService.delete(productId);
      ApiResponseMessage message=  ApiResponseMessage.builder().message("Product is deleted successfully").status(HttpStatus.OK).success(true).build();

      return new ResponseEntity<ApiResponseMessage>(message,HttpStatus.OK);
    }



    //get single

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable String productId){

        ProductDto productDto=productService.get(productId);
        return new ResponseEntity<ProductDto>(productDto,HttpStatus.OK);
    }


    //get all

    @GetMapping
  public ResponseEntity<PageableResponse <ProductDto>> getAllProduct(@RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
                                                                     @RequestParam(value = "pageSize", defaultValue = "3",required = false) int pageSize){

      return new ResponseEntity<>(productService.getAll(pageNumber, pageSize),HttpStatus.OK);
  }


    //get all live

    @GetMapping("/live")
    public ResponseEntity<PageableResponse <ProductDto>> getAllLive(@RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
                                                                       @RequestParam(value = "pageSize", defaultValue = "3",required = false) int pageSize){

        return new ResponseEntity<>(productService.getAllLive(pageNumber, pageSize),HttpStatus.OK);
    }



    //search

    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse <ProductDto>> searchProduct(@PathVariable String query,@RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
                                                                    @RequestParam(value = "pageSize", defaultValue = "3",required = false) int pageSize){

        return new ResponseEntity<>(productService.searchByTitle(query,pageNumber, pageSize),HttpStatus.OK);
    }



}
