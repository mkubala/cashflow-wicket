package pl.mkubala.cashflow.ui.panel;

import org.apache.wicket.markup.html.form.Form;

import pl.mkubala.cashflow.model.entity.Bill;
import pl.mkubala.cashflow.ui.component.BillForm;

public class BillModalWindowFormPanel extends ModalWindowFormPanel<Bill> {

    private static final long serialVersionUID = 1L;

    public BillModalWindowFormPanel(final String id, final Form<Bill> form) {
        super(id, new BillForm("form"));
    }
}
