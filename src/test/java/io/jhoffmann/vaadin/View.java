package io.jhoffmann.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class View extends VerticalLayout {

    public View() {
        TrixEditor editor = new TrixEditor("Label");
        editor.addValueChangeListener(e -> {
            Notification.show("Value has changed: " + e.getValue());
        });
        add(editor);

        Button b = new Button("Check value", e->{
            String value = editor.getValue();
            Notification.show("Value now:" + value);
        });
        add(b);
    }
}
