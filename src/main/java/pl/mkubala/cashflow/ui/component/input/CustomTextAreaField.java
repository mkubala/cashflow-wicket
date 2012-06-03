package pl.mkubala.cashflow.ui.component.input;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import pl.mkubala.cashflow.ui.component.AbstractCustomFormComponent;

public class CustomTextAreaField<T extends Serializable> extends AbstractCustomFormComponent<T> {

    private static final long serialVersionUID = 1L;

    public CustomTextAreaField(final String id, final String label, final CompoundPropertyModel<?> model) {
        this(id, new Model<String>(label), model);
    }

    public CustomTextAreaField(final String id, final IModel<String> labelModel, final CompoundPropertyModel<?> model) {
        this(id, labelModel, model.<T> bind(id));
    }

    public CustomTextAreaField(final String id, final String label, final IModel<T> model) {
        this(id, new Model<String>(label), model);
    }

    public CustomTextAreaField(final String id, final IModel<String> labelModel, final IModel<T> model) {
        super(id, labelModel, new TextArea<T>("component", model));
    }

}
