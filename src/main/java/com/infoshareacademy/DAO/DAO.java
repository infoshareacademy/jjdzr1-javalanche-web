package com.infoshareacademy.DAO;

import javax.ejb.LocalBean;
import java.util.List;

@LocalBean
public interface DAO<T> {

    List<T> getAll();

    void create(T t);

    void update(T t);

    void delete(T t);
}
