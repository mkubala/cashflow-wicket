package pl.mkubala.cashflow.ui.component;

import java.math.BigDecimal;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import pl.mkubala.cashflow.model.entity.Bill;
import pl.mkubala.cashflow.service.BillServiceImpl;
import pl.mkubala.cashflow.ui.event.AjaxFormCancelEvent;
import pl.mkubala.cashflow.ui.event.AjaxFormErrorEvent;
import pl.mkubala.cashflow.ui.event.AjaxFormSubmitEvent;

public class BillForm extends Form<Bill> {

    private static final long serialVersionUID = 1L;

    private final CompoundPropertyModel<Bill> formModel;

    @SpringBean
    private BillServiceImpl billService;

    public BillForm(final String id) {
        this(id, new Bill());
    }

    public BillForm(final String id, final Bill bill) {
        super(id, new CompoundPropertyModel<Bill>(new Model<Bill>(bill)));
        formModel = (CompoundPropertyModel<Bill>) getModel();
        setOutputMarkupId(true);
        initGui();
    }

    protected void initGui() {
        final CustomTextField<BigDecimal> amount = new CustomTextField<BigDecimal>("amount", new Model<String>("WartoÊç"),
                getFormModel());
        add(amount);

        final CustomShortDateField createDate = new CustomShortDateField("createDate", new Model<String>("Data"), getFormModel());
        createDate.getComponent().setRequired(true);
        add(createDate);

        final CustomTextAreaField<String> description = new CustomTextAreaField<String>("description", new Model<String>("Opis"),
                getFormModel());
        description.getComponent().setRequired(true);
        final StringValidator valueValidator = new StringValidator.LengthBetweenValidator(2, 4096);
        description.getComponent().add(valueValidator);
        add(description);

        add(new AjaxButton("submitButton") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                super.onSubmit();
                billService.saveBill(getBillObject());
                getParent().setDefaultModelObject(null);
                send(BillForm.this, Broadcast.BREADTH, new AjaxFormSubmitEvent(target));
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                super.onError();
                send(BillForm.this, Broadcast.BREADTH, new AjaxFormErrorEvent(target));
            }
        });

        add(new AjaxLink<Void>("cancelLink") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                getParent().setDefaultModelObject(null);
                send(BillForm.this.findParent(CustomModalWindow.class), Broadcast.BREADTH, new AjaxFormCancelEvent(target));
            }

        });
    }

    protected CompoundPropertyModel<Bill> getFormModel() {
        return formModel;
    }

    private Bill getBillObject() {
        return (Bill) getDefaultModelObject();
    }

}
