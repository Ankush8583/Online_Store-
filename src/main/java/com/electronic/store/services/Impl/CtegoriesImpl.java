package com.electronic.store.services.Impl;

import com.electronic.store.dtos.CategoriesDto;
import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.entities.Categories;
import com.electronic.store.exceptions.ResourceNotFoundException;
import com.electronic.store.helper.Helper;
import com.electronic.store.repositories.CategoriesRepository;
import com.electronic.store.services.CategoriesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CtegoriesImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoriesDto create(CategoriesDto categoriesDto) {

        String categoryId = UUID.randomUUID().toString();
        categoriesDto.setCategoryId(categoryId);

        Categories category= modelMapper.map(categoriesDto, Categories.class);
      Categories saveCategories= categoriesRepository.save(category);


        return modelMapper.map(saveCategories,CategoriesDto.class);
    }

    @Override
    public CategoriesDto update(CategoriesDto categoriesDto, String categoriesId) {

       Categories category = categoriesRepository.findById(categoriesId).orElseThrow(()-> new ResourceNotFoundException("Categories not found exception !!"));

       category.setTitle(categoriesDto.getTitle());
       category.setDescription(categoriesDto.getDescription());
       Categories updatedCategories=categoriesRepository.save(category);


       return modelMapper.map(updatedCategories,CategoriesDto.class);

    }

    @Override
    public void delete(String categoriesId) {

        Categories category = categoriesRepository.findById(categoriesId).orElseThrow(()-> new ResourceNotFoundException("Categories not found exception !!"));
        categoriesRepository.delete(category);

    }

    @Override
    public PageableResponse<CategoriesDto> getAll(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page page = categoriesRepository.findAll(pageable);


        PageableResponse pageableResponse = Helper.getPageableResponse(page, CategoriesDto.class);

        return pageableResponse;
    }


    @Override
    public CategoriesDto get(String categoriesId) {
        Categories category = categoriesRepository.findById(categoriesId).orElseThrow(()-> new ResourceNotFoundException("Categories not found exception !!"));

        return modelMapper.map(category,CategoriesDto.class);
    }
}
