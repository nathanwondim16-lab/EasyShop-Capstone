package org.yearup.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "description")
    private String description;

    @Column(name = "subcategory")
    private String subCategory;

    @Column(name = "stock")
    private int stock;

    @Column(name = "featured")
    private boolean isFeatured;

    @Column(name = "image_url")
    private String imageUrl;
}
