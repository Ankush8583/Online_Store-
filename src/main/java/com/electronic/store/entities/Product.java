package com.electronic.store.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {


    @Id
    @Column(name = "id")
    private String productId;

    private String title;

    @Column(length = 1000)
    private String description;

    private double price;

    private int quantity;

    private LocalDateTime addedDate;

    private boolean live;

    private boolean stock;

    private double discountedPrice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
   // @JoinColumn(name = "categories_id")
    private Categories categories;

}
