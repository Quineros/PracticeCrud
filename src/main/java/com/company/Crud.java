package com.company;

public interface Crud<T>{
    void create(T model);
    void update(T model);
    T find(Integer id);
    void delete(Integer id);
}
