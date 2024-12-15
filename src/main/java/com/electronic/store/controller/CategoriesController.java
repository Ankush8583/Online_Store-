package com.electronic.store.controller;


import com.electronic.store.dtos.ApiResponseMessage;
import com.electronic.store.dtos.CategoriesDto;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.services.CategoriesService;
import com.electronic.store.services.Impl.CtegoriesImpl;
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



 @PostMapping
 public ResponseEntity<CategoriesDto> createCategories(@Valid @RequestBody CategoriesDto categoriesDto){

        CategoriesDto categoriesDto1=  categoriesService.create(categoriesDto);
   return  new ResponseEntity<CategoriesDto>(categoriesDto1, HttpStatus.CREATED);
 }


 //update


    @PutMapping("/{categoriesId}")
    public ResponseEntity<CategoriesDto> updateCategories(@Valid @PathVariable("categoriesId") String categoriesId, @RequestBody CategoriesDto categoriesDto){


     CategoriesDto upadtedCategoriesDto=categoriesService.update(categoriesDto,categoriesId);
      return  new ResponseEntity<>(upadtedCategoriesDto,HttpStatus.OK);
 }



 //delete

    @DeleteMapping("/{categoriesId}")
    public ResponseEntity<ApiResponseMessage> deleteCategories(@PathVariable String categoriesId){

     categoriesService.delete(categoriesId);
    ApiResponseMessage message= ApiResponseMessage.builder().message("Categories deleted successfully !!").status(HttpStatus.OK).success(true).build();

    return new ResponseEntity<>(message,HttpStatus.OK);
 }



 //getAll

    @GetMapping
public ResponseEntity<PageableResponse <CategoriesDto>> getAllCategories(@RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
                                                                         @RequestParam(value = "pageSize", defaultValue = "3",required = false) int pageSize){


    PageableResponse categories= categoriesService.getAll(pageNumber, pageSize);
    return new ResponseEntity<>(categories,HttpStatus.OK);

}


 //getSingle



}
