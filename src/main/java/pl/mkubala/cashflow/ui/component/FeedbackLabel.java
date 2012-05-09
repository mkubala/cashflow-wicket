package pl.mkubala.cashflow.ui.component;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import pl.mkubala.cashflow.ui.event.AjaxFormErrorEvent;

@SuppressWarnings("serial")
public class FeedbackLabel extends Label {

    private final FormComponent<? extends Serializable> formComponent;

    public FeedbackLabel(final String id, final FormComponent<? extends Serializable> form) {
        this(id, form, new Model<Serializable>());
    }

    public FeedbackLabel(final String id, final FormComponent<? extends Serializable> form, final String label) {
        this(id, form, new Model<Serializable>(label));
    }

    public FeedbackLabel(final String id, final FormComponent<? extends Serializable> form,
            final IModel<? extends Serializable> model) {
        super(id, model);
        setOutputMarkupId(true);
        this.formComponent = form;
    }

    protected void onAjaxFormError(final AjaxFormErrorEvent event) {
        if (formComponent.getFeedbackMessages().isEmpty()) {
            setDefaultModelObject("");
        } else {
            setDefaultModelObject(formComponent.getFeedbackMessage().getMessage());
            add(new AttributeModifier("class", new Model<String>("feedbackLabel "
                    + formComponent.getFeedbackMessage().getLevelAsString())));
        }

        event.getTarget().add(this);
    }

    @Override
    public void onEvent(final IEvent<?> event) {
        super.onEvent(event);

        if (event.getPayload() instanceof AjaxFormErrorEvent) {
            onAjaxFormError((AjaxFormErrorEvent) event.getPayload());
        }
    }
}
