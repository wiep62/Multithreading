package com.example.springexample.services;


import com.example.springexample.dto.AuthorDto;
import com.example.springexample.entity.Author;
import com.example.springexample.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorCRUDService implements CRUDService<AuthorDto> {

    private final AuthorRepository repository;

    @Override
    public AuthorDto getById(Integer id) {
        log.info("Get Author by id: " + id);
        //т.к. мы будем по ИД получать АТОРА то нам нужно указать не обжект а автор
        // и нам нужно создать статические методы маппинга
        return mapToDto(repository.findById(id).orElseThrow());
    }

    @Override
    public Collection<AuthorDto> getAll() {
        return  repository.findAll()
                .stream()
                .map(AuthorCRUDService::mapToDto)
                .toList();

    }

    @Override
    public void create(AuthorDto authorDto) {
Author author = mapToEntity(authorDto);
repository.save(author);
    }

    @Override
    public void update(AuthorDto authorDto) {
        repository.save( mapToEntity(authorDto));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
public static Author mapToEntity(AuthorDto authorDto) {
    //создаем автора:
    Author author = new Author();
    author.setId(authorDto.getId());
    author.setFirstName(authorDto.getFirstName());
    author.setLastName(authorDto.getLastName());
    author.setRating(authorDto.getRating());
    author.setComments(authorDto.getComments()
            .stream()
            .map(CommentCRUDService::mapToEntity)
            .toList()); //получаем список коментариев, а нам нужен список объектов класса коммент ДТО, нужно преобразовать с помощью стрима
return author;
    }
public static AuthorDto mapToDto(Author author) {
    AuthorDto authorDto = new AuthorDto();
    authorDto.setId(author.getId());
    authorDto.setFirstName(author.getFirstName());
    authorDto.setLastName(author.getLastName());
    authorDto.setRating(author.getRating());
    authorDto.setComments(author.getComments()
            .stream()
            .map(CommentCRUDService::mapToDto)
            .toList()); //получаем список коментариев, а нам нужен список объектов класса коммент ДТО, нужно преобразовать с помощью стрима
    return authorDto;
}
}
