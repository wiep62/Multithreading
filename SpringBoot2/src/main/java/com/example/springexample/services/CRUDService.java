package com.example.springexample.services;

import java.util.Collection;

public interface CRUDService<T> {
    //получ элемента по id:
    T getById(Integer id);
    //получение списка:
    Collection<T> getAll();
    //метод создания элемента:
void create(T item);
   // обновления элемента по id
    void update(T item);
  //  удаления элемента по id
    void delete(Integer id);


}
