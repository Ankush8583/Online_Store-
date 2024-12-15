package com.electronic.store.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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

}
