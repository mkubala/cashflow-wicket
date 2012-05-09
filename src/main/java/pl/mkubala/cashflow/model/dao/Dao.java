package pl.mkubala.cashflow.model.dao;

import java.io.Serializable;
import java.util.List;

import pl.mkubala.cashflow.model.QueryParam;

public interface Dao<T extends Serializable> {

    T get(Long id);

    void save(T entity);

    void delete(Long id);

    void delete(T entity);

    List<? extends T> find(QueryParam queryParam, T filter);

    List<? extends T> find(QueryParam queryParam);

    List<? extends T> findAll();
}
