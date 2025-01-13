package com.electronic.store.dtos;

import com.electronic.store.entities.Categories;
import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString



@Builder
public class ProductDto {


    private String productId;

    private String title;


    private String description;

    private double price;

    private int quantity;

    private LocalDateTime addedDate;

    private boolean live;

    private boolean stock;

    private double discountedPrice;
    private Categories categories;

}
