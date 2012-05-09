package pl.mkubala.cashflow.ui.event;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class AjaxFormSubmitEvent extends AjaxFormEvent {

    public AjaxFormSubmitEvent(final AjaxRequestTarget target) {
        super(target);
    }

}
