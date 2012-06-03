package pl.mkubala.cashflow.ui.event;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class AjaxShowGridModalEvent extends AjaxFormEvent {

    private final Long entityId;

    public AjaxShowGridModalEvent(final AjaxRequestTarget target) {
        this(target, null);
    }

    public AjaxShowGridModalEvent(final AjaxRequestTarget target, final Long entityId) {
        super(target);
        this.entityId = entityId;
    }

    public Long getEntityId() {
        return entityId;
    }

}
