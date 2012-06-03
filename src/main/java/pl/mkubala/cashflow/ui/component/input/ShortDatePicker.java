package pl.mkubala.cashflow.ui.component.input;

import org.apache.wicket.extensions.yui.calendar.DatePicker;

@SuppressWarnings("serial")
public class ShortDatePicker extends DatePicker {

    @Override
    protected String getDatePattern() {
        return "yyyy/MM/dd";
    }

}
