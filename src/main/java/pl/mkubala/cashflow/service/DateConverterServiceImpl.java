package pl.mkubala.cashflow.service;

import java.util.Date;

import org.apache.wicket.datetime.DateConverter;
import org.apache.wicket.datetime.PatternDateConverter;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DateConverterServiceImpl implements DateConverterService {

    private static final DateConverter shortConverter = new PatternDateConverter("yyyy/MM/dd", false);

    @Override
    public DateConverter getShortConverter() {
        return shortConverter;
    }

    @Override
    public String convert(final Date date) {
        return shortConverter.convertToString(date, LocaleContextHolder.getLocale());
    }

    @Override
    public Date convert(final String value) {
        return shortConverter.convertToObject(value, LocaleContextHolder.getLocale());
    }

}
