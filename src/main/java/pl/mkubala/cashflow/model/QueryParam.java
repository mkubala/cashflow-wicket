package pl.mkubala.cashflow.model;

import java.io.Serializable;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

public class QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int first;

    private final int count;

    private final String sort;

    private final boolean sortAsc;

    public QueryParam(final SortParam sortParam) {
        this(0, 0, sortParam.getProperty(), sortParam.isAscending());
    }

    public QueryParam(final int first, final int count, final String sort, final boolean sortAsc) {
        this.first = first;
        this.count = count;
        this.sort = sort;
        this.sortAsc = sortAsc;
    }

    public void apply(final Criteria criteria) {
        criteria.setFirstResult(first);
        if (count != 0) {
            criteria.setMaxResults(count);
        }
        criteria.addOrder(getOrder());
    }

    public Order getOrder() {
        if (sortAsc) {
            return Order.asc(sort);
        }
        return Order.desc(sort);
    }

    public int getFirst() {
        return first;
    }

    public int getCount() {
        return count;
    }

}
