package pl.mkubala.cashflow.model.dao;

import org.springframework.stereotype.Repository;

import pl.mkubala.cashflow.model.entity.Bill;

@Repository
public class BillDaoImpl extends AbstractHibernateDao<Bill> implements BillDao {

    @Override
    protected Class<Bill> getEntityClass() {
        return Bill.class;
    }

}
