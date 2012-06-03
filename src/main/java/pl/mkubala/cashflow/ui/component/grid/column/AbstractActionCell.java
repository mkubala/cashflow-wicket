package pl.mkubala.cashflow.ui.component.grid.column;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import pl.mkubala.cashflow.model.Identifiable;
import pl.mkubala.cashflow.ui.component.Initializable;
import pl.mkubala.cashflow.ui.component.grid.GridComponent;
import pl.mkubala.cashflow.ui.event.AjaxShowGridModalEvent;

public abstract class AbstractActionCell<T extends Identifiable> extends Panel implements Initializable {

    private static final long serialVersionUID = 1L;

    public AbstractActionCell(final String id, final IModel<?> model) {
        super(id, model);
    }

    @Override
    public void initialize() {
        addComponents();
    }

    protected abstract void addComponents();

    protected void showEditModal(final AjaxRequestTarget target) {
        @SuppressWarnings("unchecked")
        final T entity = (T) AbstractActionCell.this.getDefaultModelObject();
        AbstractActionCell.this.send(AbstractActionCell.this.findParent(GridComponent.class), Broadcast.EXACT,
                new AjaxShowGridModalEvent(target, entity.getId()));
    }

}