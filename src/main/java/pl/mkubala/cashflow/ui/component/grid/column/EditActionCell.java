package pl.mkubala.cashflow.ui.component.grid.column;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;

import pl.mkubala.cashflow.model.Identifiable;

public class EditActionCell<T extends Identifiable> extends AbstractActionCell<T> {

    private static final long serialVersionUID = 1L;

    public EditActionCell(final String id, final IModel<?> model) {
        super(id, model);
    }

    protected void addComponents() {
        add(new AjaxLink<Void>("edit") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                showEditModal(target);
            }
        });
    }

}