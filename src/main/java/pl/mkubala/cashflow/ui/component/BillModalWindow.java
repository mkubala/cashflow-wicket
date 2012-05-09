package pl.mkubala.cashflow.ui.component;

import org.apache.wicket.markup.html.form.Form;

import pl.mkubala.cashflow.model.entity.Bill;
import pl.mkubala.cashflow.ui.panel.BillModalWindowFormPanel;
import pl.mkubala.cashflow.ui.panel.ModalWindowFormPanel;

public class BillModalWindow extends ModalFormWindow<Bill> {

    private static final long serialVersionUID = 1L;

    public BillModalWindow(final String id) {
        super(id);
    }

    @Override
    protected Form<Bill> buildForm(final String formId) {
        return new BillForm(formId);
    }

    @Override
    protected ModalWindowFormPanel<Bill> buildFormPanel(final String id) {
        return new BillModalWindowFormPanel(id, buildForm("form"));
    }

}
