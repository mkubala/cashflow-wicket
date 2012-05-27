package pl.mkubala.cashflow.ui.component;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import pl.mkubala.cashflow.ui.event.AjaxFormCancelEvent;
import pl.mkubala.cashflow.ui.event.AjaxFormEvent;
import pl.mkubala.cashflow.ui.event.AjaxFormSubmitEvent;
import pl.mkubala.cashflow.ui.panel.AbstractModalFormPanel;

public class ModalFormWindow<T> extends CustomModalWindow {

    private static final long serialVersionUID = 1L;

    private final AbstractModalFormPanel<T> modalFormPanel;

    public ModalFormWindow(final String id, final AbstractModalFormPanel<T> modalFormPanel) {
        super(id);
        setCookieName(id);
        setAutoSize(true);
        modalFormPanel.setMarkupId(getContentId());
        this.modalFormPanel = modalFormPanel;
        setContent(this.modalFormPanel);
    }

    public void show(final AjaxRequestTarget target, final String title, final T entity) {
        show(target, new Model<String>(title), entity);
    }

    public void show(final AjaxRequestTarget target, final IModel<String> title, final T entity) {
        modalFormPanel.setDefaultModelObject(entity);
        setTitle(title);
        super.show(target);
    }

    protected void onFormSubmit(final AjaxRequestTarget target) {
        close(target);
    }

    protected void onFormCancel(final AjaxRequestTarget target) {
        close(target);
    }

    @Override
    public void onEvent(final IEvent<?> event) {
        final Object payload = event.getPayload();

        if (payload instanceof AjaxFormSubmitEvent) {
            onFormSubmit(((AjaxFormEvent) payload).getTarget());
        } else if (payload instanceof AjaxFormCancelEvent) {
            onFormCancel(((AjaxFormEvent) payload).getTarget());
        }
    }

}
