package pl.mkubala.cashflow.ui.component;

import pl.mkubala.cashflow.model.entity.Bill;
import pl.mkubala.cashflow.ui.panel.BillModalWindowFormPanel;
import pl.mkubala.cashflow.ui.panel.ModalWindowFormPanel;

public class BillModalWindow extends ModalFormWindow<Bill> {

    private static final long serialVersionUID = 1L;

    public BillModalWindow(final String id) {
        super(id);
        setTitle("Przyk¸adowy tajtl");
        setAutoSize(true);
        setResizable(false);
    }

    @Override
    protected ModalWindowFormPanel<Bill> buildFormPanel(final String id) {
        return new BillModalWindowFormPanel(id, new BillForm("form"));
    }

}
