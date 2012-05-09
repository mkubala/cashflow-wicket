package pl.mkubala.cashflow.ui.component;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;

import pl.mkubala.cashflow.ui.event.AjaxFormCancelEvent;
import pl.mkubala.cashflow.ui.event.AjaxFormEvent;
import pl.mkubala.cashflow.ui.event.AjaxFormSubmitEvent;
import pl.mkubala.cashflow.ui.panel.ModalWindowFormPanel;

@SuppressWarnings("serial")
public abstract class ModalFormWindow<T> extends ModalWindow {

    private ModalWindowFormPanel<T> formPanel;

    public ModalFormWindow(final String id) {
        super(id);
        setCookieName(id);
        setAutoSize(true);
    }

    public void init() {
        // formPanel = new ModalWindowFormPanel<T>(this, buildForm("form"));
        formPanel = buildFormPanel(getContentId());
        setContent(formPanel);
    }

    protected abstract ModalWindowFormPanel<T> buildFormPanel(final String id);

    protected abstract Form<T> buildForm(final String formId);

    public void setFormEntity(final T entity) {
        if (formPanel == null) {
            init();
        }
        formPanel.setFormEntity(entity);
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
