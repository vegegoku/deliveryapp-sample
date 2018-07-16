package org.dominokit.samples;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.infoboxes.InfoBox;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.style.ColorScheme;

import static org.jboss.gwt.elemento.core.Elements.p;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
        Layout layout = Layout.create("RAMI APP").show(ColorScheme.PINK);

        layout.getContentPanel().appendChild(Card.create("FIRST CARD", "this is a sample card")
                .appendContent(p().textContent("Quis pharetra a pharetra fames blandit. Risus faucibus velit Risus imperdiet mattis neque volutpat, etiam lacinia netus dictum magnis per facilisi sociosqu. Volutpat. Ridiculus nostra."))
        .asElement());
    }
}
