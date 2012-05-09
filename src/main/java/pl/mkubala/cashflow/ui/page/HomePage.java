package pl.mkubala.cashflow.ui.page;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import pl.mkubala.cashflow.service.HelloService;

public class HomePage extends BasePage {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private HelloService helloService;

    public HomePage(final PageParameters parameters) {
        super(parameters);
        add(new Label("message", new Model<String>(helloService.sayHello())));
        add(new Link<FormPage>("link") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(FormPage.class);
            }

        });
    }
}
