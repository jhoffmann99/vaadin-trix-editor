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
    private final Element labelElement;
    private final Element hiddenInputElement;
    private final Element editorElement;

    @Override
    protected void setPresentationValue(String s) {
        editorElement.executeJs("this.editor.loadHTML($0)", s);
    }

    public  TrixEditor(String initialValue) {
        this(initialValue, null);
    }

    public TrixEditor(String initialValue, String labelText) {
        super(initialValue);

        labelElement = new Element("label");
        hiddenInputElement = new Element("input");
        editorElement = new Element("trix-editor");

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



        editorElement.addEventListener("trix-attachment-add", e-> {
           editorElement.executeJs("this.attachments = this.editorController.attachmentManager.getAttachments();");
        });

        if(labelText != null){
            labelElement.setText(labelText);
            getElement().appendChild(labelElement);
        }

        getElement().appendChild(hiddenInputElement, editorElement);

        // Workaround: as long as file attachments do not work, hide the button
        disableFileAttachments();
    }

    public void disableFileAttachments() {
        getElement().executeJs("document.querySelector('.trix-button-group.trix-button-group--file-tools').style.display = 'none';");
    }


}
