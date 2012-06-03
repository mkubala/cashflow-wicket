package pl.mkubala.cashflow.ui.component.grid;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.GoAndClearFilter;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.TextFilteredPropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

import pl.mkubala.cashflow.model.BillDataProvider;
import pl.mkubala.cashflow.model.entity.Bill;
import pl.mkubala.cashflow.ui.component.BillModalFormPanel;
import pl.mkubala.cashflow.ui.component.grid.column.ActionColumnFactory;
import pl.mkubala.cashflow.ui.component.grid.column.ActionsColumn;
import pl.mkubala.cashflow.ui.component.grid.column.EditActionCell;

import com.google.common.collect.Lists;

public class BillsGrid extends AbstractGridComponent<Bill> {

    private static final long serialVersionUID = 1L;

    public BillsGrid(final String id) {
        super(id, new BillDataProvider(), new BillModalFormPanel("content"));
    }

    @Override
    protected List<IColumn<Bill>> getColumns() {
        List<IColumn<Bill>> columns = Lists.newLinkedList();
        columns.add(new ActionsColumn<Bill>("Actions", new ActionColumnFactory<Bill>() {

            @Override
            public Panel buildRow(final String componentId, final IModel<Bill> rowModel) {
                return new EditActionCell<Bill>(componentId, rowModel);
            }

            @Override
            public Panel buildFilterHeader(final String componentId, final FilterForm<?> form) {
                return new GoAndClearFilter(componentId, form, new Model<String>("Filter"), new Model<String>("Clear"));
            }
        }));

        columns.add(new TextFilteredPropertyColumn<Bill, String>(new ResourceModel("amount"), "amount", "amount"));
        columns.add(new TextFilteredPropertyColumn<Bill, String>(new ResourceModel("descripton"), "descripton", "descripton"));

        return columns;
    }

}
