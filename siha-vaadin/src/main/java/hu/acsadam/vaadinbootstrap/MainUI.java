package hu.acsadam.vaadinbootstrap;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.DiscoveryNavigator;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@Theme("valo")
@Component("MainUI")
@Scope("prototype")
@Widgetset("hu.acsadam.vaadinbootstrap.AppWidgetSet")
public class MainUI extends UI {
	private static final Logger logger = Logger.getLogger(MainUI.class);

	private final HorizontalLayout main = new HorizontalLayout();
	private final VerticalLayout content = new VerticalLayout();


	public MainUI() {
		super();
		Locale locale = new Locale("hu", "HU");
		setLocale(locale);
		VaadinSession.getCurrent().setLocale(locale);
	}

	@Override
	protected void init(VaadinRequest request) {
		main.setSizeFull();
		main.addStyleName("main-layout");
		setSizeFull();

		setupNavigator();

		content.setSizeFull();
		main.addComponent(content);

		setContent(main);
	}

	private void setupNavigator() {
		DiscoveryNavigator navigator = new DiscoveryNavigator(this, content);
		setNavigator(navigator);
	}


	@Override
	public DiscoveryNavigator getNavigator() {
		return (DiscoveryNavigator) super.getNavigator();
	}

}
