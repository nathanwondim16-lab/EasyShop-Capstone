package org.yearup.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}