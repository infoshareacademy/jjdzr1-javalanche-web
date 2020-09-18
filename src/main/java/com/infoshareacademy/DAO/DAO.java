package com.infoshareacademy.DAO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DAO<T> {

    List<T> getAll();

    void create(T t);

    void update(T t);

    void delete(T t);
}
