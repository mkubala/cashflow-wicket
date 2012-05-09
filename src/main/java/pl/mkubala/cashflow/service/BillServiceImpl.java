package pl.mkubala.cashflow.service;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mkubala.cashflow.model.QueryParam;
import pl.mkubala.cashflow.model.dao.BillDao;
import pl.mkubala.cashflow.model.entity.Bill;

@Service
@Transactional
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;

    @Override
    public void saveBill(final Bill bill) {
        billDao.save(bill);
    }

    @Override
    public Bill get(final Long id) {
        return billDao.get(id);
    }

    @Override
    public List<? extends Bill> getBills(final Bill filter) {
        return getBills(null, filter);
    }

    @Override
    public List<? extends Bill> getBills(final SortParam sortParam, final Bill filter) {
        return getBills(0, 0, sortParam, filter);
    }

    @Override
    public List<? extends Bill> getBills(final int first, final int count, final SortParam sortParam, final Bill filter) {
        QueryParam queryParam = null;
        if (sortParam == null) {
            queryParam = new QueryParam(first, count, "createDate", false);
        } else {
            queryParam = new QueryParam(first, count, sortParam.getProperty(), sortParam.isAscending());
        }
        return billDao.find(queryParam, filter);
    }

}
