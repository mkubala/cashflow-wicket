package pl.mkubala.cashflow.ui.component;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

public class CustomModalWindow extends ModalWindow {

    private static final long serialVersionUID = 1L;

    public CustomModalWindow(final String id) {
        super(id);
    }

    public CustomModalWindow(final String id, final IModel<?> model) {
        super(id, model);
    }

    @Override
    protected ResourceReference newCssResource() {
        return new PackageResourceReference(CustomModalWindow.class, "CustomModalWindow.css");
    }

}
