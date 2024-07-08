package com.yangyag.msa.category.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    public void setName(String name) {
        this.name = name;
    }
}
