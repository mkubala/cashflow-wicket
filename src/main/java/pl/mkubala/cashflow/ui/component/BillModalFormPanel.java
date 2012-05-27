package pl.mkubala.cashflow.ui.component;

import java.math.BigDecimal;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import pl.mkubala.cashflow.model.entity.Bill;
import pl.mkubala.cashflow.service.BillServiceImpl;
import pl.mkubala.cashflow.ui.panel.AbstractModalFormPanel;

public class BillModalFormPanel extends AbstractModalFormPanel<Bill> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private BillServiceImpl billService;

    public BillModalFormPanel(final String id) {
        this(id, new Bill());
    }

    public BillModalFormPanel(final String id, final Bill bill) {
        super(id, new CompoundPropertyModel<Bill>(new Model<Bill>(bill)));
    }

    @Override
    public void initGui() {
        super.initGui();
        final CustomTextField<BigDecimal> amount = new CustomTextField<BigDecimal>("amount", new Model<String>("WartoÊç"),
                getFormModel());
        getForm().add(amount);

        final CustomShortDateField createDate = new CustomShortDateField("createDate", new Model<String>("Data"), getFormModel());
        createDate.getComponent().setRequired(true);
        getForm().add(createDate);

        final CustomTextAreaField<String> description = new CustomTextAreaField<String>("description", new Model<String>("Opis"),
                getFormModel());
        description.getComponent().setRequired(true);
        final StringValidator valueValidator = new StringValidator.LengthBetweenValidator(2, 4096);
        description.getComponent().add(valueValidator);
        getForm().add(description);
    }

    @Override
    protected void onModalFormSubmit(final AjaxRequestTarget target, final Form<?> form) {
        billService.saveBill((Bill) getDefaultModelObject());
    }

}
