package pl.mkubala.cashflow.ui.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import pl.mkubala.cashflow.model.entity.Bill;
import pl.mkubala.cashflow.ui.component.BillModalFormPanel;
import pl.mkubala.cashflow.ui.component.ModalFormWindow;
import pl.mkubala.cashflow.ui.panel.BillsPanel;

public class FormPage extends BasePage {

    private static final long serialVersionUID = 1L;

    public FormPage(final PageParameters parameters) {
        super(parameters);
        initGui();
    }

    private void initGui() {
        final BillModalFormPanel billModalFormPanel = new BillModalFormPanel("content");
        billModalFormPanel.initGui();
        final ModalFormWindow<Bill> billFormWindow = new ModalFormWindow<Bill>("window", billModalFormPanel);

        add(new AjaxLink<Void>("showWindow") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                billFormWindow.show(target, "Nowy rachunek", new Bill());
            }

            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("class", "button create");
            }
        });

        add(billFormWindow);

        final BillsPanel billsPanel = new BillsPanel("bills");
        billsPanel.setBillFormWindow(billFormWindow);
        add(billsPanel);
    }
}
