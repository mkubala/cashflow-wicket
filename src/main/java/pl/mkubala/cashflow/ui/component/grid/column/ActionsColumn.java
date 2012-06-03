package pl.mkubala.cashflow.ui.component.grid.column;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilteredAbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import pl.mkubala.cashflow.model.Identifiable;

public class ActionsColumn<T extends Identifiable> extends FilteredAbstractColumn<T> {

    private static final long serialVersionUID = 1L;

    private final ActionColumnFactory<T> rowFactory;

    public ActionsColumn(final String label, final ActionColumnFactory<T> rowActionPanelFactory) {
        this(new Model<String>(label), rowActionPanelFactory);
    }

    public ActionsColumn(final IModel<String> label, final ActionColumnFactory<T> actionPanel) {
        super(label);
        this.rowFactory = actionPanel;
    }

    @Override
    public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId, final IModel<T> rowModel) {
        cellItem.add(rowFactory.buildRow(componentId, rowModel));
    }

    @Override
    public Component getFilter(final String componentId, final FilterForm<?> form) {
        return rowFactory.buildFilterHeader(componentId, form);
    }
}
