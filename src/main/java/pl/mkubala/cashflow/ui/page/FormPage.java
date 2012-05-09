package pl.mkubala.cashflow.ui.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import pl.mkubala.cashflow.ui.component.BillModalWindow;
import pl.mkubala.cashflow.ui.panel.BillsPanel;

public class FormPage extends BasePage {

    private static final long serialVersionUID = 1L;

    public FormPage(final PageParameters parameters) {
        super(parameters);
        initGui();
    }

    private void initGui() {
        final BillModalWindow billFormWindow = new BillModalWindow("window");
        billFormWindow.init();

        add(new AjaxLink<Void>("showWindow") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                billFormWindow.show(target);
            }
        });

        add(billFormWindow);

        final BillsPanel billsPanel = new BillsPanel("bills");
        billsPanel.setBillFormWindow(billFormWindow);
        add(billsPanel);
    }
}
