package pl.mkubala.cashflow.model;

import static org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder.DESCENDING;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import pl.mkubala.cashflow.model.entity.Bill;
import pl.mkubala.cashflow.service.BillService;

@SuppressWarnings("serial")
@Configurable
public class SortableBillDataProvider extends SortableDataProvider<Bill> implements IFilterStateLocator<Bill> {

    @Autowired
    private transient BillService billService;

    private Bill filterState = new Bill(null, null, null);

    public SortableBillDataProvider() {
        setSort("createDate", DESCENDING);
    }

    @Override
    public Iterator<? extends Bill> iterator(final int first, final int count) {
        return billService.getBills(first, count, getSort(), getFilterState()).iterator();
    }

    @Override
    public int size() {
        return billService.getBills(getFilterState()).size();
    }

    @Override
    public IModel<Bill> model(final Bill bill) {
        return new LoadableDetachableBillModel(bill);
    }

    @Override
    public Bill getFilterState() {
        return filterState;
    }

    @Override
    public void setFilterState(final Bill filterState) {
        this.filterState = filterState;
    }

}
