package org.vaadin.addons.jhoffmann99;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.testbench.TestBenchElement;

public class ViewIT extends AbstractViewTest {

    @Test
    public void componentWorks() {
        final TestBenchElement editor = $("jhoffmann-trix").waitForFirst();

        Assert.assertTrue(
                editor.$(TestBenchElement.class).all().size() > 0);
    }
}
