package com.example.springexample.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "author")
public class Author {
       //для того чтобы это поле в БД было и первичным ключом и при вставке новых записей увеличивалась автоматически
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
    private Integer id;
    @Column(name = "firstName")
  private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "rating")
    private Long rating;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
