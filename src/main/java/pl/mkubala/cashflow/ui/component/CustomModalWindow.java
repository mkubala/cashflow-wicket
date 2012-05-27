package pl.mkubala.cashflow.ui.component;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import pl.mkubala.cashflow.ui.event.AjaxFormCancelEvent;

public class CustomModalWindow extends ModalWindow {

    private static final long serialVersionUID = 1L;

    public CustomModalWindow(final String id) {
        super(id);
        init();
    }

    public CustomModalWindow(final String id, final IModel<?> model) {
        super(id, model);
        init();
    }

    @SuppressWarnings("serial")
    private void init() {
        setAutoSize(true);
        setResizable(false);
        setCloseButtonCallback(new CloseButtonCallback() {

            @Override
            public boolean onCloseButtonClicked(final AjaxRequestTarget target) {
                send(CustomModalWindow.this, Broadcast.BREADTH, new AjaxFormCancelEvent(target));
                return true;
            }
        });
    }

    @Override
    protected ResourceReference newCssResource() {
        return new PackageResourceReference(CustomModalWindow.class, "CustomModalWindow.css");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
        response.renderJavaScriptReference(new JavaScriptResourceReference(CustomModalWindow.class,
                "CustomModalWindowAdditions.js"));
    }

}
