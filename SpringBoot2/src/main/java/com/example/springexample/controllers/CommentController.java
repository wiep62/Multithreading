package com.example.springexample.controllers;

import com.example.springexample.dto.CommentDto;
import com.example.springexample.services.CommentCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentCRUDService commentService;


    public CommentController(CommentCRUDService commentService) {
        this.commentService = commentService;
    }

    //получение по ИД: получать по ИД будем в ответ на гет запрос, он будет содержать переменную id
    // путь  @GetMapping("/{id}") будет добавлен к пути @RequestMapping("/comment")
    //для того чтобы идентификатор здесь прочитать допишем аннотацию @PathVariable Integer id
    @GetMapping("/{id}")
    public CommentDto getCommentById(@PathVariable Integer id) {
return commentService.getById(id);
    }
//пропишем метод получения списка: просто возвр-ет при помощи метода getAll все коментарии/ указываем     @GetMapping("/") или можно просто     @GetMapping(" ")
    @GetMapping("/")
    public Collection<CommentDto> getAllComments() {
        return commentService.getAll();
    }
//создание (ничего не возвр-ет), нам нужен весь коментарий, поэтому возьмем более сложный вариант RequestBody и в теле запроса будем передавать поля, ключи и значения в JSON формате,
    //которые будут автоматиченский преобразованы в объект класса CommentDto.:

    @PostMapping
    public void createComment(@RequestBody CommentDto commentDto) {
commentService.create(commentDto);
    }

    //обнавление: ТУТ МЫ ПОЛУЧАЕМ 2 ПАРМЕТРА, 1-ЫЙ ИДЕНТИФИКАТОР ТОГО ЧТО МЫ ОБНОВЛЯЕМ, А 2-ОЙ - В ТЕЛЕ ЗАПРОСА ДАННЫЕ САМОГО КОМЕНТАРИЯ в формате JSON:
  //id будем передавать в строке запроса:
    @PutMapping("/{id}")
    public void updateComment(@PathVariable Integer id, @RequestBody CommentDto commentDto) {
        commentDto.setId(id);
commentService.update(commentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.delete(id);
    }

}
