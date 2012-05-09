package pl.mkubala.cashflow.ui.event;

import org.apache.wicket.ajax.AjaxRequestTarget;

public abstract class AjaxFormEvent {

    private final AjaxRequestTarget target;

    public AjaxFormEvent(final AjaxRequestTarget target) {
        this.target = target;
    }

    public AjaxRequestTarget getTarget() {
        return target;
    }
}
