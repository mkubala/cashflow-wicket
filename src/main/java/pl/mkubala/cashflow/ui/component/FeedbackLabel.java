package pl.mkubala.cashflow.ui.component;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import pl.mkubala.cashflow.ui.event.AjaxFormCancelEvent;
import pl.mkubala.cashflow.ui.event.AjaxFormErrorEvent;
import pl.mkubala.cashflow.ui.event.AjaxFormSubmitEvent;

import com.google.common.base.Preconditions;

/**
 * Component for displaying form component related messages.
 * 
 * @author Marcin Kubala
 * 
 */
@SuppressWarnings("serial")
public class FeedbackLabel extends Label {

    private final FormComponent<? extends Serializable> formComponent;

    private final AttributeModifier errorAttributeModifier = new AddErrorClassAttributeModifier("class", new Model<String>(
            "error"));

    /**
     * Create new FeedBackLabel object.
     * 
     * @param id
     *            component's identifier
     * @param formComponent
     *            related formComponent which will be messages provider
     * 
     * @see org.apache.wicket.Component#Component(String, IModel)
     * 
     * @throws NullPointerException
     *             if given formComponent is null.
     */
    public FeedbackLabel(final String id, final FormComponent<? extends Serializable> formComponent) {
        this(id, formComponent, new Model<String>());
    }

    /**
     * Create new FeedBackLabel object.
     * 
     * @param id
     *            component's identifier
     * @param formComponent
     *            related formComponent which will be messages provider
     * @param message
     * 
     * @see org.apache.wicket.Component#Component(String, IModel)
     * 
     * @throws NullPointerException
     *             if given formComponent is null.
     */
    public FeedbackLabel(final String id, final FormComponent<? extends Serializable> formComponent, final String message) {
        this(id, formComponent, new Model<String>(message));
    }

    /**
     * Create new FeedBackLabel object.
     * 
     * @param id
     *            component's identifier
     * @param formComponent
     *            related formComponent which will be messages provider
     * @param messageModel
     * 
     * @see org.apache.wicket.Component#Component(String, IModel)
     * 
     * @throws NullPointerException
     *             if given formComponent is null.
     */
    public FeedbackLabel(final String id, final FormComponent<? extends Serializable> formComponent,
            final IModel<String> messageModel) {
        super(id, messageModel);
        setOutputMarkupId(true);
        Preconditions.checkNotNull(formComponent, "Given form component should not be null.");
        this.formComponent = formComponent;
    }

    protected void onAjaxFormError(final AjaxFormErrorEvent event) {
        if (formComponent.hasFeedbackMessage()) {
            setDefaultModelObject(formComponent.getFeedbackMessage().getMessage().toString());
            formComponent.add(errorAttributeModifier);
        } else {
            clear();
        }

        event.getTarget().add(this, formComponent);
    }

    protected void clear() {
        if (formComponent.getBehaviors().contains(errorAttributeModifier)) {
            formComponent.remove(errorAttributeModifier);
        }
        setDefaultModelObject("");
    }

    @Override
    public void onEvent(final IEvent<?> event) {
        super.onEvent(event);
        if (event.getPayload() instanceof AjaxFormErrorEvent) {
            onAjaxFormError((AjaxFormErrorEvent) event.getPayload());
        } else if (event.getPayload() instanceof AjaxFormCancelEvent || event.getPayload() instanceof AjaxFormSubmitEvent) {
            clear();
        }
    }

    public static class AddErrorClassAttributeModifier extends AttributeModifier {

        public AddErrorClassAttributeModifier(final String attribute, final IModel<?> replaceModel) {
            super(attribute, replaceModel);
        }

        @Override
        protected String newValue(final String currentValue, final String additionalValue) {
            String newValue = currentValue;
            if (StringUtils.isBlank(currentValue)) {
                newValue = additionalValue;
            } else if (!StringUtils.contains(currentValue, additionalValue)) {
                newValue = new StringBuilder(currentValue).append(' ').append(additionalValue).toString();
            }
            return newValue;
        }
    }

    // public static class RemoveErrorClassAttributeModifier extends AttributeModifier {
    //
    // public RemoveErrorClassAttributeModifier(final String attribute, final IModel<?> replaceModel) {
    // super(attribute, replaceModel);
    // }
    //
    // @Override
    // protected String newValue(final String currentValue, final String valueToDelete) {
    // String newValue = currentValue;
    // if (StringUtils.contains(currentValue, valueToDelete)) {
    // newValue = StringUtils.remove(currentValue, valueToDelete);
    // }
    // return newValue;
    // }
    // }
}
