package pl.mkubala.cashflow.ui.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import pl.mkubala.cashflow.ui.component.CustomModalWindow;
import pl.mkubala.cashflow.ui.event.AjaxFormCancelEvent;
import pl.mkubala.cashflow.ui.event.AjaxFormErrorEvent;
import pl.mkubala.cashflow.ui.event.AjaxFormSubmitEvent;

public abstract class AbstractModalFormPanel<T> extends Panel {

    private static final long serialVersionUID = 1L;

    private final Form<T> form;

    public AbstractModalFormPanel(final String id, final CompoundPropertyModel<T> model) {
        super(id, model);
        form = new Form<T>("form", model);
        setOutputMarkupId(true);
    }

    protected Form<T> getForm() {
        return form;
    }

    @SuppressWarnings("unchecked")
    protected CompoundPropertyModel<T> getFormModel() {
        return (CompoundPropertyModel<T>) form.getDefaultModel();
    }

    protected abstract void onModalFormSubmit(final AjaxRequestTarget target, final Form<?> form);

    protected void onModalFormError(final AjaxRequestTarget target, final Form<?> form) {
    }

    protected void onModalFormCancel(final AjaxRequestTarget target) {
    }

    public void initGui() {
        addControls();
        add(getForm());
    }

    protected void addControls() {
        form.add(new AjaxButton("submitButton") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                super.onSubmit();
                AbstractModalFormPanel.this.onModalFormSubmit(target, form);
                send(AbstractModalFormPanel.this.findParent(ModalWindow.class).getParent(), Broadcast.BREADTH,
                        new AjaxFormSubmitEvent(target));
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                super.onError();
                AbstractModalFormPanel.this.onModalFormError(target, form);
                send(AbstractModalFormPanel.this.findParent(ModalWindow.class), Broadcast.BREADTH, new AjaxFormErrorEvent(target));
            }
        });

        form.add(new AjaxLink<Void>("cancelLink") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                getParent().setDefaultModelObject(null);
                AbstractModalFormPanel.this.onModalFormCancel(target);
                send(AbstractModalFormPanel.this.findParent(CustomModalWindow.class), Broadcast.BREADTH, new AjaxFormCancelEvent(
                        target));
            }

        });
    }

}
