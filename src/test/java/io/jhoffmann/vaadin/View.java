package io.jhoffmann.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class View extends VerticalLayout {

    public View() {
        TrixEditor editor = new TrixEditor("Label");

        editor.setValue("Initial <i>content</i>");

        editor.addValueChangeListener(e -> {
            Notification.show("Value has changed: " + e.getValue());
        });
        add(editor);

        Button b = new Button("Check current value", e->{
            String value = editor.getValue();
            Notification.show("Value now:" + value);
        });
        Button b1 = new Button("Set value to foobar", e->{
            editor.setValue("<b>foo</b>bar");
        });
        add(b, b1);
    }
}
