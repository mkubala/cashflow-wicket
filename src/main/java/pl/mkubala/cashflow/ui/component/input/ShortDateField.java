package pl.mkubala.cashflow.ui.component.input;

import java.util.Date;

import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DateField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

@SuppressWarnings("serial")
public class ShortDateField extends DateField {

    public ShortDateField(final String id) {
        this(id, null);
    }

    public ShortDateField(final String id, final IModel<Date> model) {
        super(id, model);
    }

    @Override
    protected DateTextField newDateTextField(String id, PropertyModel<Date> dateFieldModel) {
        return new DateTextField(id, dateFieldModel, new PatternDateConverter("yyyy/MM/dd", false));
    }

    @Override
    protected DatePicker newDatePicker() {
        DatePicker datePicker = new ShortDatePicker();
        datePicker.setShowOnFieldClick(true);
        datePicker.setAutoHide(true);
        return datePicker;
    }

}
