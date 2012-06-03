package pl.mkubala.cashflow.ui.component.grid;

import java.util.Date;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilteredPropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.TextFilteredPropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import pl.mkubala.cashflow.model.SortableFilterableDataProvider;
import pl.mkubala.cashflow.model.entity.Entity;
import pl.mkubala.cashflow.ui.component.Initializable;
import pl.mkubala.cashflow.ui.component.ModalFormWindow;
import pl.mkubala.cashflow.ui.component.input.ShortDateField;
import pl.mkubala.cashflow.ui.event.AjaxFormSubmitEvent;
import pl.mkubala.cashflow.ui.event.AjaxShowGridModalEvent;
import pl.mkubala.cashflow.ui.panel.AbstractModalFormPanel;

public abstract class AbstractGridComponent<T extends Entity> extends Panel implements GridComponent<T>, Initializable {

    private static final long serialVersionUID = 1L;

    private final ModalFormWindow<T> modal;

    private AjaxFallbackDefaultDataTable<T> table;

    private final SortableFilterableDataProvider<T> dataProvider;

    public AbstractGridComponent(final String id, final SortableFilterableDataProvider<T> dataProvider,
            final AbstractModalFormPanel<T> modalForm) {
        super(id);
        this.modal = new ModalFormWindow<T>("window", modalForm);
        this.dataProvider = dataProvider;
    }

    @Override
    public void initialize() {
        @SuppressWarnings("serial")
        final FilterForm<T> filterForm = new FilterForm<T>("filter-form", dataProvider) {

            @Override
            protected void onSubmit() {
                table.setCurrentPage(0);
            }
        };

        table = new AjaxFallbackDefaultDataTable<T>("table", getColumns(), dataProvider, 10) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onEvent(final IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof AjaxFormSubmitEvent) {
                    onAjaxFormSubmitEvent((AjaxFormSubmitEvent) event.getPayload());
                }
            };

            private void onAjaxFormSubmitEvent(final AjaxFormSubmitEvent event) {
                detach();
                event.getTarget().add(this);
            }

        };

        table.addTopToolbar(new FilterToolbar(table, filterForm, dataProvider));
        table.addBottomToolbar(new NavigationToolbar(table));
        table.setOutputMarkupId(true);

        filterForm.add(table);
        add(filterForm);

    }

    protected abstract List<IColumn<T>> getColumns();

    @Override
    public void onEvent(final IEvent<?> event) {
        super.onEvent(event);
        if (event.getPayload() instanceof AjaxShowGridModalEvent) {
            showGridModal((AjaxShowGridModalEvent) event.getPayload());
        }
    }

    public void showGridModal(final AjaxShowGridModalEvent event) {
        T entity = null;
        Long entityId = event.getEntityId();
        if (entityId != null) {
            entity = dataProvider.get(entityId);
        }
        modal.show(event.getTarget(), "title", entity);
    }

    protected TextFilteredPropertyColumn<T, String> createFilteredColumn(final String key, final String sortProperty,
            final String propertyExpr) {
        return new TextFilteredPropertyColumn<T, String>(new ResourceModel(key), sortProperty, propertyExpr);
    }

    public abstract static class DateFilteredPropertyColumn<T extends Entity> extends FilteredPropertyColumn<T> {

        private static final long serialVersionUID = 1L;

        public DateFilteredPropertyColumn() {
            super(new Model<String>("Date"), "createDate", "createDate");
        }

        @Override
        public void populateItem(final Item<ICellPopulator<T>> item, final String componentId, final IModel<T> rowModel) {
            // final Date createDate = rowModel.getObject().getCreateDate();
            // final Model<String> createDateModel = new Model<String>(dateConverterService.convert(createDate));
            // item.add(new Label(componentId, createDateModel));
        }

        @Override
        public Component getFilter(final String componentId, final FilterForm<?> form) {
            final IModel<Date> model = new PropertyModel<Date>(form.getDefaultModel(), "createDate");
            return new ShortDateField(componentId, model);
        }

    }

    @Override
    public ModalFormWindow<T> getModal() {
        return modal;
    }

    @Override
    public void setItemsPerPage(final int itemsPerPage) {
        table.setItemsPerPage(itemsPerPage);
    }

}
