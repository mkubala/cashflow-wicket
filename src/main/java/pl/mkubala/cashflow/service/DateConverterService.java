package pl.mkubala.cashflow.service;

import java.util.Date;

import org.apache.wicket.datetime.DateConverter;

public interface DateConverterService {

    DateConverter getShortConverter();

    String convert(Date date);

    Date convert(String value);
}
