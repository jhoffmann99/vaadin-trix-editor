package org.vaadin.addons.jhoffmann99;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.dom.Element;

import java.util.UUID;

@Tag("jhoffmann-trix")
@JsModule("trix/dist/trix.esm.min.js")
@NpmPackage(value = "trix", version = "2.0.4")
@CssImport("trix/dist/trix.css")
@CssImport("./custom.css")
public class TrixEditor extends AbstractField<TrixEditor, String> {
    Element labelElement = new Element("label");
    Element hiddenInputElement = new Element("input");
    Element editorElement = new Element("trix-editor");

    @Override
    protected void setPresentationValue(String s) {
        editorElement.executeJs("this.editor.loadHTML($0)", s);
    }

    public  TrixEditor(String initialValue) {
        this(initialValue, null);
    }

    public TrixEditor(String initialValue, String labelText) {
        super(initialValue);

        hiddenInputElement.setAttribute("type", "hidden");
        String inputid = UUID.randomUUID().toString();
        hiddenInputElement.setAttribute("id", inputid);

        editorElement.setAttribute("input", inputid);
        /*
         * Impl note: if too much communication with this,
         * either only delta's should be transferred or
         * value should be only synhcronized "on blur"
         */
        editorElement.addEventListener("trix-change", e-> {
            String value = e.getEventData().getString("event.target.value");
            setModelValue(value, true);
        })
                .addEventData("event.target.value")
                .debounce(1000)
        ;

        if(labelText != null){
            labelElement.setText(labelText);
            getElement().appendChild(labelElement);
        }

        getElement().appendChild(hiddenInputElement, editorElement);
    }

}
