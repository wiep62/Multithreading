package com.example.springexample.services;

import com.example.springexample.dto.CommentDto;
import com.example.springexample.entity.Comment;
import com.example.springexample.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.TreeMap;
@RequiredArgsConstructor
@Slf4j
@Service
public class CommentCRUDService implements CRUDService<CommentDto> {

    // @Value("${comment.length.max}") //
    // private Integer lengthMax; //переменная в которую попадает файл конфигурации

    //   private final TreeMap<Integer, CommentDto> storage = new TreeMap<>();
    private final CommentRepository repository;

    @Override
    public CommentDto getById(Integer id) {
        log.info("Getting comment by id: " + id);
        Comment comment = repository.findById(id).orElseThrow();
        return mapToDto(comment);
    }

    //получение всего списка:
    @Override
    public Collection<CommentDto> getAll() {
        log.info("Getting all comments");
        return repository.findAll()
                .stream()
                .map(CommentCRUDService::mapToDto)
                .toList();
    }

    //создание элемента: нужно добавить элемент с определенным идентификатором и установить этому элементу соотв-ий идентификатор
    //берем текущийий максимальный ключ и прибавим к нему 1.
    //у нашего коментария установим идентификатор и добавим его с СТОРЕДЖ
    @Override
    public void create(CommentDto item) {
        log.info("Creating comment: " + item);
        Comment comment = mapToEntity(item);
        repository.save(comment);
    }

    //обновление:
    //для того чтобы обновить элемент под указанным id мы можем положить его в storage , но лочше у item установить в явном виде этот идентификатор (неизвестно какой может придти параметр)
    @Override
    public void update(CommentDto item) {
        log.info("Updating comment: ");
        Comment comment = mapToEntity(item);
        repository.save(comment);
    }

    //удаление:
    @Override
    public void delete(Integer id) {
        log.info("Deleting comment: " + id);
        repository.deleteById(id);
    }

    public static CommentDto mapToDto(Comment comment) {
//чтобы КОММЕНТгетИД заработал, нужно добавить геттеры и сеттеры в СОММЕНТ.ДЖАВА
        CommentDto commentDto = new CommentDto(); //для того чтобы можно было использовать конструктор без аргументов, нужно указать в АННОТАЦИИ КомментДТО без аргументов
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
      //  commentDto.setAuthor(comment.getAuthor());
        commentDto.setAuthor(AuthorCRUDService.mapToDto(comment.getAuthor()));

        return commentDto;
    }

    //обратная операция:
    public static Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        comment.setAuthor(AuthorCRUDService.mapToEntity(commentDto.getAuthor()));
        return comment;
    }
}