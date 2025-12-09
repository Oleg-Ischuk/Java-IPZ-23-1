package com.education.ztu;

import java.util.List;

public abstract class AbstractDAO<T> {
    public abstract void insert(T obj);
    public abstract T getById(int id);
    public abstract List<T> getAll();
    public abstract void update(T obj);
    public abstract void delete(int id);
}
