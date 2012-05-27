package pl.mkubala.cashflow.ui.component;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IFormVisitorParticipant;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.google.common.base.Preconditions;

public abstract class AbstractCustomFormComponent<T extends Serializable> extends Panel implements IFormVisitorParticipant {

    private static final long serialVersionUID = 1L;

    private final Label label;

    private final FormComponent<T> wrappedComponent;

    private final FeedbackLabel feedbackLabel;

    public AbstractCustomFormComponent(final String id, final String labelValue, final FormComponent<T> componentToWrap) {
        this(id, new Model<String>(labelValue), componentToWrap);
    }

    public AbstractCustomFormComponent(final String id, final IModel<String> labelModel, final FormComponent<T> componentToWrap) {
        super(id);
        Preconditions.checkNotNull(componentToWrap, "Given form component should not be null.");

        this.label = new Label("label", labelModel);
        add(this.label);

        this.wrappedComponent = componentToWrap;
        this.wrappedComponent.setOutputMarkupId(true);
        this.wrappedComponent.setMarkupId(getMarkupId() + "-component");
        add(this.wrappedComponent);

        this.feedbackLabel = new FeedbackLabel("feedbackLabel", wrappedComponent);
        add(this.feedbackLabel);
    }

    public FormComponent<T> getComponent() {
        return wrappedComponent;
    }

    protected Label getLabel() {
        return label;
    }

    protected FeedbackLabel getFeedbackLabel() {
        return feedbackLabel;
    }

    @Override
    public boolean processChildren() {
        return true;
    }

}
