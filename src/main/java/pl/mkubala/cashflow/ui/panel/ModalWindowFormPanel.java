package pl.mkubala.cashflow.ui.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings("serial")
public class ModalWindowFormPanel<T> extends Panel {

    private final Form<T> form;

    public ModalWindowFormPanel(final String id, final Form<T> form) {
        super(id);
        this.form = form;
        add(form);
    }

    public void setFormEntity(final T entity) {
        form.setDefaultModelObject(entity);
    }

}
