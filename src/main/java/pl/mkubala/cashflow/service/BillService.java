package pl.mkubala.cashflow.service;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import pl.mkubala.cashflow.model.entity.Bill;

public interface BillService {

    void saveBill(Bill bill);

    List<? extends Bill> getBills(Bill filter);

    List<? extends Bill> getBills(SortParam sortParam, Bill filter);

    List<? extends Bill> getBills(int offset, int length, SortParam sortParam, Bill filter);

    Bill get(Long id);

}
