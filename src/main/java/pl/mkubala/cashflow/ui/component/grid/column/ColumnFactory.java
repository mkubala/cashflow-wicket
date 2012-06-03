package pl.mkubala.cashflow.ui.component.grid.column;

import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import pl.mkubala.cashflow.model.Identifiable;

public interface ColumnFactory<T extends Identifiable> {

    Panel buildRow(final String componentId, final IModel<T> rowModel);

    Panel buildFilterHeader(final String componentId, final FilterForm<?> form);
}
