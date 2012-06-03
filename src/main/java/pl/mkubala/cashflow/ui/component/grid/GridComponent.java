package pl.mkubala.cashflow.ui.component.grid;

import org.apache.wicket.event.IEventSink;

import pl.mkubala.cashflow.model.entity.Entity;
import pl.mkubala.cashflow.ui.component.ModalFormWindow;

public interface GridComponent<T extends Entity> extends IEventSink {

    ModalFormWindow<T> getModal();

    void setItemsPerPage(final int itemsPerPage);

}
