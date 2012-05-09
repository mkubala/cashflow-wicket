package pl.mkubala.cashflow.model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.mkubala.cashflow.model.QueryParam;

@Repository
public abstract class AbstractHibernateDao<T extends Serializable> implements Dao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected abstract Class<T> getEntityClass();

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public T get(final Long id) {
        return (T) getSession().get(getEntityClass(), id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<? extends T> find(final QueryParam queryParam) {
        return find(queryParam, null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<? extends T> find(final QueryParam queryParam, final T filter) {
        return getResultList(buildCriteriaQuery(queryParam, filter));
    }

    @Override
    @Transactional(readOnly = true)
    public List<? extends T> findAll() {
        return find(null);
    }

    protected Criteria buildCriteriaQuery(final QueryParam queryParam, final T filter) {
        Criteria criteria = getSession().createCriteria(getEntityClass());
        if (filter != null) {
            criteria.add(Example.create(filter));
        }
        if (queryParam != null) {
            queryParam.apply(criteria);
        }
        return criteria;
    }

    @SuppressWarnings("unchecked")
    protected List<? extends T> getResultList(final Criteria criteria) {
        return criteria.list();
    }

    @Override
    public void save(final T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(final T entity) {
        getSession().delete(entity);
    }

    @Override
    public void delete(final Long id) {
        delete(get(id));
    }

}
