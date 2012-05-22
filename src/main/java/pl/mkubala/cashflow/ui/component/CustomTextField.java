package pl.mkubala.cashflow.ui.component;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class CustomTextField<T extends Serializable> extends AbstractCustomFormComponent<T> {

    private static final long serialVersionUID = 1L;

    public CustomTextField(final String id, final String label, final CompoundPropertyModel<?> model) {
        this(id, new Model<String>(label), model);
    }

    public CustomTextField(final String id, final IModel<String> labelModel, final CompoundPropertyModel<?> model) {
        this(id, labelModel, model.<T> bind(id));
    }

    public CustomTextField(final String id, final String label, final IModel<T> model) {
        this(id, new Model<String>(label), model);
    }

    public CustomTextField(final String id, final IModel<String> labelModel, final IModel<T> model) {
        super(id, labelModel, new TextField<T>("component", model));
    }

}
