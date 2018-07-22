package org.dominokit.samples;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.forms.TextArea;
import org.dominokit.domino.ui.header.BlockHeader;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.infoboxes.InfoBox;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.Style;
import org.gwtproject.i18n.shared.DateTimeFormat;
import org.jboss.gwt.elemento.core.IsElement;

import java.util.Date;

import static org.jboss.gwt.elemento.core.Elements.div;

public class DeliveryManagementComponent implements IsElement<HTMLDivElement> {

    private HTMLDivElement element = div().asElement();
    private InfoBox receiverBox;
    private InfoBox whenBox;
    private InfoBox whereBox;
    private ReceiverComponent.Person receiver;
    private Date shipmentDate = new Date();

    public DeliveryManagementComponent() {

        receiverBox = Style.of(InfoBox.create(Icons.ALL.person(), "RECEIVER", "Select person")
                .setBackground(Color.BLUE)
                .setHoverEffect(InfoBox.HoverEffect.ZOOM))
                .setProperty("cursor", "pointer").get();

        whenBox = Style.of(InfoBox.create(Icons.ALL.date_range(), "WHEN", "Today")
                .setBackground(Color.INDIGO)
                .setHoverEffect(InfoBox.HoverEffect.ZOOM))
                .setProperty("cursor", "pointer").get();

        whereBox = Style.of(InfoBox.create(Icons.ALL.location_on(), "WHERE", "Dubai, Marina, Marina sail, building 3, Apartment 9012 (default)")
                .setBackground(Color.ORANGE)
                .setHoverEffect(InfoBox.HoverEffect.ZOOM))
                .setProperty("cursor", "pointer").get();

        element.appendChild(BlockHeader.create("MANAGE YOUR DELIVERY").asElement());
        element.appendChild(receiverBox.asElement());
        element.appendChild(whenBox.asElement());
        element.appendChild(whereBox.asElement());
        element.appendChild(Style.of(Card.create()).setPaddingTop("20px").get()
                .appendChild(TextArea.create("Special instructions")
                        .autoSize()
                        .setLeftAddon(Icons.ALL.assignment())
                        .setRows(5))
                .asElement());

        element.appendChild(Button.createSuccess("CONFIRM ALL").block().large()
                .asElement());
    }

    public InfoBox getReceiverBox() {
        return receiverBox;
    }

    public InfoBox getWhenBox() {
        return whenBox;
    }

    public InfoBox getWhereBox() {
        return whereBox;
    }

    @Override
    public HTMLDivElement asElement() {
        return element;
    }

    public void setReceiver(ReceiverComponent.Person person) {
        receiverBox.getValueElement().textContent = person.getName();
        this.receiver = person;
    }

    public void setDate(Date date) {
        whenBox.getValueElement().textContent = DateTimeFormat.getFormat("dd/MM/yyyy").format(date);
        this.shipmentDate = date;
    }
}
