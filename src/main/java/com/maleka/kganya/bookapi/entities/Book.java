package com.maleka.kganya.bookapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data               // Includes @Getter, @Setter, @ToString, @EqualsAndHashCode
@Builder
@NoArgsConstructor  // Default constructor
@AllArgsConstructor // All-args constructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    @Column(name = "published_year")
    private int publishedYear;
    private boolean available = true;
}

