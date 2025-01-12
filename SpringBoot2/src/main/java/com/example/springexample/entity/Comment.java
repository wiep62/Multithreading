package com.example.springexample.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {
    //идентификатор коментария
    @Id //1-й ключ для таблицы "коммент"
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
      //текст коментария
      @Column(name = "text")
      private String text;
    //автор коментария
    @ManyToOne
    @JoinColumn(name = "author_id")
  //  private String author;
    private Author author;


    @CreationTimestamp //используется для автомат-ой установки времени создания записи в базе
    @Column(name = "creation_time")
private LocalDateTime time;
}

