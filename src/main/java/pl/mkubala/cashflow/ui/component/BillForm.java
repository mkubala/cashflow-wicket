package pl.mkubala.cashflow.ui.component;

import java.math.BigDecimal;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextArea;
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

    @SpringBean
    private BillServiceImpl billService;

    public BillForm(final String id) {
        this(id, new Bill());
    }

    public BillForm(final String id, final Bill bill) {
        super(id, new CompoundPropertyModel<Bill>(new Model<Bill>(bill)));
        initGui();
    }

    protected void initGui() {
        setOutputMarkupId(true);

        Label amountLabel = new Label("amountLabel", "WartoÊç");
        add(amountLabel);

        Label createDateLabel = new Label("createDateLabel", "Data");
        add(createDateLabel);

        Label valueLabel = new Label("descriptionLabel", "Opis");
        add(valueLabel);

        final NumberTextField<BigDecimal> amount = new NumberTextField<BigDecimal>("amount");
        amount.setRequired(true);
        add(amount);
        add(new FeedbackLabel("amountFeedback", amount));

        final ShortDateField createDate = new ShortDateField("createDate");
        createDate.setRequired(true);
        add(createDate);

        FeedbackLabel createDateFeedback = new FeedbackLabel("createDateFeedback", createDate);
        add(createDateFeedback);

        final TextArea<String> description = new TextArea<String>("description");
        description.setRequired(true);
        StringValidator valueValidator = new StringValidator.LengthBetweenValidator(2, 4096);
        description.add(valueValidator);
        add(description);

        FeedbackLabel feedbackLabel = new FeedbackLabel("descriptionFeedback", description);
        add(feedbackLabel);

        add(new AjaxButton("submitButton") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                super.onSubmit();
                billService.saveBill(getBillObject());
                getParent().setDefaultModelObject(null);
                send(getPage(), Broadcast.BREADTH, new AjaxFormSubmitEvent(target));
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                super.onError();
                send(getPage(), Broadcast.BREADTH, new AjaxFormErrorEvent(target));
            }
        });

        add(new AjaxLink<Void>("cancelLink") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                getParent().setDefaultModelObject(null);
                send(getPage(), Broadcast.BREADTH, new AjaxFormCancelEvent(target));
            }

        });
    }

    private Bill getBillObject() {
        return (Bill) getDefaultModelObject();
    }

}
