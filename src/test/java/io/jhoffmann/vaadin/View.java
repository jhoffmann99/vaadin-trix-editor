package io.jhoffmann.vaadin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class View extends VerticalLayout {

    public View() {
        TrixEditor editor = new TrixEditor("Label");
        add(editor);
    }
}
