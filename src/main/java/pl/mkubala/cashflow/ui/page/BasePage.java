package pl.mkubala.cashflow.ui.page;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.common.collect.Lists;

public abstract class BasePage extends WebPage {

    private static final long serialVersionUID = 1L;

    public BasePage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
        addCssResources(response);
    }

    private void addCssResources(final IHeaderResponse response) {
        for (String cssUrl : Lists.newArrayList("style.css", "form.css", "table.css", "ribbon.css", "calendar.css")) {
            response.renderCSSReference("/resources/css/" + cssUrl);
        }
    }

}
