package com.si.ha.vaadin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.vaadin.diagrambuilder.DiagramBuilder;
import org.vaadin.diagrambuilder.Node;
import org.vaadin.diagrambuilder.NodeType;
import org.vaadin.diagrambuilder.Transition;

import ru.xpoft.vaadin.VaadinView;

import com.google.common.eventbus.Subscribe;
import com.si.ha.events.DeviceCreatedEvent;
import com.si.ha.events.EventBus;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@VaadinView(BuilderView.NAME)
@org.springframework.stereotype.Component
@Scope("prototype")
@JavaScript("http://cdn.alloyui.com/2.5.0/aui/aui-min.js")
@StyleSheet("http://cdn.alloyui.com/2.5.0/aui-css/css/bootstrap.min.css")
public class BuilderView extends AbstractView {
	public static final String NAME = "builder";

	private DiagramBuilder db;

	public BuilderView() {
		EventBus.register(this);
		db = new DiagramBuilder();
	}

	@Subscribe
	public void onDeviceCreation(final DeviceCreatedEvent event) {
		EventBus.post(new PushEvent() {

			@Override
			public void run() {
				List<NodeType> fields = new ArrayList<>();
				fields.addAll(Arrays.asList(db.getAvailableFields()));
				fields.add( new NodeType(
                        "diagram-node-condition-icon",
                        event.getDevice().getName(),
                        "condition"
                ));
				 db.setAvailableFields(fields.toArray(new NodeType[0]));
			}
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		VerticalLayout l = new VerticalLayout();
		l.setSizeFull();
		db.setAvailableFields(
				new NodeType(
						"diagram-node-start-icon",
						"Start",
						"start"
				),
				new NodeType(
						"diagram-node-task-icon",
						"Task",
						"task"
				)
				);
		db.setFields(new Node(
				"StartNode",
				"start",
				10, 10
				));

		db.setTransitions(
				new Transition("start", "task", "TaskConnector1"));

		db.setSizeFull();
		l.addComponent(db);
		setCompositionRoot(l);
	}
}
