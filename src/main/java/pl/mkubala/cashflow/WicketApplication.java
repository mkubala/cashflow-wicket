package pl.mkubala.cashflow;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import pl.mkubala.cashflow.ui.page.FormPage;
import pl.mkubala.cashflow.ui.page.HomePage;

@Service(value = "wicketApplication")
public class WicketApplication extends WebApplication {

    private static final String DEFAULT_ENCODING = "UTF-8";

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext, true));

        getMarkupSettings().setDefaultMarkupEncoding(DEFAULT_ENCODING);
        getRequestCycleSettings().setResponseRequestEncoding(DEFAULT_ENCODING);

        mountPage("/bills", FormPage.class);
    }

}
