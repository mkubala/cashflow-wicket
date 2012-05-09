package pl.mkubala.cashflow.ui.event;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class AjaxFormErrorEvent extends AjaxFormEvent {

    public AjaxFormErrorEvent(final AjaxRequestTarget target) {
        super(target);
    }
}
