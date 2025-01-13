package com.electronic.store.repositories;

import com.electronic.store.entities.Categories;
import com.electronic.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {

    //search

    Page<Product> findByTitleContaining(String subTitle,Pageable pageable);

   Page<Product> findByLiveTrue(Pageable pageable);

   Page<Product> findByCategories(Categories categories,Pageable pageable);

}
