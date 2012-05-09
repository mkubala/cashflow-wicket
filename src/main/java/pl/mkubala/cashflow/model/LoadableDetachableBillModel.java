package pl.mkubala.cashflow.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.wicket.model.LoadableDetachableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import pl.mkubala.cashflow.model.entity.Bill;
import pl.mkubala.cashflow.service.BillService;

@SuppressWarnings("serial")
@Configurable
public class LoadableDetachableBillModel extends LoadableDetachableModel<Bill> {

    private final Long id;

    @Autowired
    private transient BillService billService;

    public LoadableDetachableBillModel(final Bill bill) {
        this(bill.getId());
    }

    public LoadableDetachableBillModel(final Long id) {
        this.id = id;
    }

    @Override
    protected Bill load() {
        return billService.get(id);
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).build();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LoadableDetachableBillModel other = (LoadableDetachableBillModel) obj;
        return new EqualsBuilder().append(id, other.getId()).build();
    }

}
