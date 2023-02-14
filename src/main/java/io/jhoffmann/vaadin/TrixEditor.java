package io.jhoffmann.vaadin;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.PropertyChangeEvent;

@Tag("jhoffmann-trix")
@JsModule("trix/dist/trix.esm.min.js")
@NpmPackage(value = "trix", version = "2.0.4")
@CssImport("trix/dist/trix.css")
@CssImport("./custom.css")
public class TrixEditor extends AbstractField<TrixEditor, String> {
    Element labelElement = new Element("label");

    Element hiddenInputElement = new Element("input");
    Element editorElement = new Element("trix-editor");

    private static PropertyDescriptor<String, String>
            VALUE = PropertyDescriptors
            .propertyWithDefault("value", "");

    public String getValue() {
        System.out.println("getValue()");
        System.out.println("value:");
        System.out.println(get(VALUE));
        return get(VALUE);
    }
    public void setValue(String value) {
        System.out.println("setValue()");
        System.out.println("value:");
        System.out.println(value);
        set(VALUE, value);
    }

    @Override
    protected void setPresentationValue(String value) {
        System.out.println("setPresentationValue");
        System.out.println("value:");
        System.out.println(value);
        hiddenInputElement.setAttribute("value", value);

        Element element = getElement();

        if(value == null) {
            element.removeProperty("text");
        } else {
            element.setProperty("text", value);
        }

    }

    private void setupProperty(String name, String event) {
        Element element = getElement();

        element.addPropertyChangeListener(name, event,
                this::propertyUpdated);
    }

    private void propertyUpdated(
            PropertyChangeEvent event) {
        Element element = getElement();

        String text = element.getProperty("text", "");

            setModelValue(text, event.isUserOriginated());
    }

    public TrixEditor(String labelText) {
        super("");
        labelElement.setText(labelText);

        hiddenInputElement.setAttribute("type", "hidden");
        hiddenInputElement.setAttribute("id", "x");

        editorElement.setAttribute("input", "x");

        Element element = getElement();
        element.appendChild(hiddenInputElement);
        element.appendChild(editorElement);


        setupProperty("text", "text-changed");
    }
}
