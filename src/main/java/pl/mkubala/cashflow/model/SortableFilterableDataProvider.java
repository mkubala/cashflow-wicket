package pl.mkubala.cashflow.model;

import java.io.Serializable;

import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;

@SuppressWarnings("serial")
public abstract class SortableFilterableDataProvider<T extends Serializable> extends SortableDataProvider<T> implements
        IFilterStateLocator<T> {

    public abstract T get(final long id);

}
