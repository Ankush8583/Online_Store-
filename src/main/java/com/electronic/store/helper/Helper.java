package com.electronic.store.helper;

import java.util.List;
import java.util.stream.Collectors;

import com.electronic.store.dtos.CategoriesDto;
import org.hibernate.query.Page;
import org.modelmapper.ModelMapper;

import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.UserDto;
import org.springframework.data.domain.Pageable;

public class Helper {

    public static <U,D>PageableResponse<D> getPageableResponse(org.springframework.data.domain.Page<U> page, Class<D> type){

                    List<U> entity=page.getContent();

                 List<D> dtoList=entity.stream().map(Object -> new ModelMapper().map(Object, type)).collect(Collectors.toList());

                PageableResponse<D> response = new PageableResponse<>();
          response.setContent(dtoList);
          response.setPageNumber(page.getNumber());
          response.setPageSize(page.getSize());
          response.setTotalElement(page.getTotalElements());
          response.setTotalPages(page.getTotalPages());
          response.setLastPage(page.isLast());

          return response;

    }


}
