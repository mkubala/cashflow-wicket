package pl.mkubala.cashflow.ui.component;

import java.util.Date;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class CustomShortDateField extends AbstractCustomFormComponent<Date> {

    private static final long serialVersionUID = 1L;

    public CustomShortDateField(final String id, final String label, final CompoundPropertyModel<?> model) {
        this(id, new Model<String>(label), model);
    }

    public CustomShortDateField(final String id, final IModel<String> labelModel, final CompoundPropertyModel<?> model) {
        this(id, labelModel, model.<Date> bind(id));
    }

    public CustomShortDateField(final String id, final String label, final IModel<Date> model) {
        this(id, new Model<String>(label), model);
    }

    public CustomShortDateField(final String id, final IModel<String> labelModel, final IModel<Date> model) {
        super(id, labelModel, new ShortDateField("component", model));
    }

}
