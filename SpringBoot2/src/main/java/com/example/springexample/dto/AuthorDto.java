package com.example.springexample.dto;

import com.example.springexample.entity.Comment;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;
@Data
public class AuthorDto {
    private Integer id;
    @Column(name = "id")
    private String firstName;
    @Column(name = "id")
    private String lastName;
    @Column(name = "id")
    private Long rating;
    private List<CommentDto> Comments;
}
