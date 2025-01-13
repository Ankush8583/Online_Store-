package com.electronic.store.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Categories {

    @Id
    @Column(name = "id")
    private String categoryId;

    @Column(name = "category_title",length = 70, nullable = false)
    private String title;

    @Column(name = "category_description",length = 60)
    private  String description;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

}
