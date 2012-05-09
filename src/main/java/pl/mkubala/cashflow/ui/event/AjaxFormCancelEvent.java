package pl.mkubala.cashflow.ui.event;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class AjaxFormCancelEvent extends AjaxFormEvent {

    public AjaxFormCancelEvent(final AjaxRequestTarget target) {
        super(target);
    }
}
