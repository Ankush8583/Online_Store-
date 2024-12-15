package com.electronic.store.repositories;

import com.electronic.store.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories,String>
{


}
