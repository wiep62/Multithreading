package com.example.springexample.controllers;

import com.example.springexample.dto.AuthorDto;
import com.example.springexample.entity.Author;
import com.example.springexample.services.AuthorCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorCRUDService authorService;

    @GetMapping
    public Collection<AuthorDto> getAll() {
return authorService.getAll();
    }

public void create (@RequestBody AuthorDto authorDto) {
        //делаем чтобы тело получаемого запроса автоматически преобразовывалось в объект классса AuthorDto, для этого нужно в скобках перед этим словом написать реквест бади
        authorService.create(authorDto);
}1111

}
