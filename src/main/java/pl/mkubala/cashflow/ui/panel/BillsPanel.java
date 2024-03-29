package pl.mkubala.cashflow.ui.panel;

import java.util.Date;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilteredAbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilteredPropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.GoAndClearFilter;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.TextFilteredPropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import pl.mkubala.cashflow.model.BillDataProvider;
import pl.mkubala.cashflow.model.entity.Bill;
import pl.mkubala.cashflow.service.DateConverterService;
import pl.mkubala.cashflow.ui.component.Initializable;
import pl.mkubala.cashflow.ui.component.ModalFormWindow;
import pl.mkubala.cashflow.ui.component.input.ShortDateField;
import pl.mkubala.cashflow.ui.event.AjaxFormSubmitEvent;

import com.google.common.collect.Lists;

public class BillsPanel extends Panel implements Initializable {

    private static final long serialVersionUID = 1L;

    private ModalFormWindow<Bill> relatedModalFormWindow;

    private AjaxFallbackDefaultDataTable<Bill> bills;

    private BillDataProvider dataProvider;

    @SpringBean
    private DateConverterService dateConverterService;

    public BillsPanel(final String id) {
        super(id);
    }

    public BillsPanel(final String id, final IModel<?> model) {
        super(id, model);
    }

    @Override
    public void initialize() {
        initGui();
    }

    private void initGui() {
        dataProvider = new BillDataProvider();

        @SuppressWarnings("serial")
        final FilterForm<Bill> form = new FilterForm<Bill>("filter-form", dataProvider) {

            @Override
            protected void onSubmit() {
                bills.setCurrentPage(0);
            }
        };

        bills = new AjaxFallbackDefaultDataTable<Bill>("bills", createBillsColumns(), dataProvider, 10) {

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
        bills.addTopToolbar(new FilterToolbar(bills, form, dataProvider));

        bills.addBottomToolbar(new NavigationToolbar(bills));

        bills.setOutputMarkupId(true);

        form.add(bills);
        add(form);
    }

    private List<IColumn<Bill>> createBillsColumns() {
        final List<IColumn<Bill>> billColumns = Lists.newArrayList();
        billColumns.add(new BillActionsColumn());
        billColumns.add(new DateFilteredPropertyColumn());
        billColumns.add(createFilteredColumn("amount", "amount", "amount"));
        billColumns.add(createFilteredColumn("description", "description", "description"));
        return billColumns;
    }

    protected TextFilteredPropertyColumn<Bill, String> createFilteredColumn(final String key, final String sortProperty,
            final String propertyExpr) {
        return new TextFilteredPropertyColumn<Bill, String>(new ResourceModel(key), sortProperty, propertyExpr);
    }

    public void setBillFormWindow(final ModalFormWindow<Bill> billFormWindow) {
        this.relatedModalFormWindow = billFormWindow;
    }

    private class DateFilteredPropertyColumn extends FilteredPropertyColumn<Bill> {

        private static final long serialVersionUID = 1L;

        public DateFilteredPropertyColumn() {
            super(new Model<String>("Date"), "createDate", "createDate");
        }

        @Override
        public void populateItem(final Item<ICellPopulator<Bill>> item, final String componentId, final IModel<Bill> rowModel) {
            final Date createDate = rowModel.getObject().getCreateDate();
            final Model<String> createDateModel = new Model<String>(dateConverterService.convert(createDate));
            item.add(new Label(componentId, createDateModel));
        }

        @Override
        public Component getFilter(final String componentId, final FilterForm<?> form) {
            final IModel<Date> model = new PropertyModel<Date>(form.getDefaultModel(), "createDate");
            return new ShortDateField(componentId, model);
        }

    }

    private class BillActionsColumn extends FilteredAbstractColumn<Bill> {

        private static final long serialVersionUID = 1L;

        public BillActionsColumn() {
            super(new Model<String>("Actions"));
        }

        @Override
        public void populateItem(final Item<ICellPopulator<Bill>> cellItem, final String componentId, final IModel<Bill> rowModel) {
            cellItem.add(new ActionPanel(componentId, rowModel));
        }

        @Override
        public Component getFilter(final String componentId, final FilterForm<?> form) {
            return new GoAndClearFilter(componentId, form, new Model<String>("Filter"), new Model<String>("Clear"));
        }
    }

    private class ActionPanel extends Panel {

        private static final long serialVersionUID = 1L;

        public ActionPanel(final String id, final IModel<?> model) {
            super(id, model);
            add(new AjaxLink<Void>("edit") {

                private static final long serialVersionUID = 1L;

                @Override
                public void onClick(final AjaxRequestTarget target) {
                    final Bill currentBill = (Bill) ActionPanel.this.getDefaultModelObject();
                    relatedModalFormWindow.show(target, "edytuj rachunek", currentBill);
                }
            });
        }

    }

}
