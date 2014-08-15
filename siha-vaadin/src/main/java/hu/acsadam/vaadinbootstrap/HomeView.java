package hu.acsadam.vaadinbootstrap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;


@Component
@Scope("prototype")
@VaadinView(HomeView.NAME)
public class HomeView extends AbstractView implements View {
	public static final String NAME = "";


	public HomeView() {

	}


	@Override
	public void enter(ViewChangeEvent event) {
		setCompositionRoot(new Label("Hello world!"));
	}
}
