package org.dominokit.samples;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.collapsible.Collapsible;
import org.dominokit.domino.ui.forms.*;
import org.dominokit.domino.ui.header.BlockHeader;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.Style;
import org.jboss.gwt.elemento.core.IsElement;

import static java.util.Objects.nonNull;
import static org.jboss.gwt.elemento.core.Elements.div;

public class ShipmentAddressComponent implements IsElement<HTMLDivElement> {

    private HTMLDivElement element = div().asElement();
    private AddressSelectionHandler addressSelectionHandler;

    public ShipmentAddressComponent() {
        element.appendChild(BlockHeader.create("CHANGE ADDRESS").asElement());

        RadioGroup radioGroup=RadioGroup.create("address");
        radioGroup
                .addRadio(Radio.create("default", "Dubai, Marina, Marina sail, building 3, Apartment 9012 (default)").withGap().check())
                .addRadio(Radio.create("new", "New address").withGap())
                .addRadio(Radio.create("pickup-point", "Select a pickup point").withGap());

        HTMLDivElement newAddressElement = div()
                .add(TextBox.create("City").setLeftAddon(Icons.ALL.location_city()))
                .add(TextBox.create("Area").setLeftAddon(Icons.ALL.location_on()))
                .add(TextBox.create("Address").setLeftAddon(Icons.ALL.map())).asElement();

        HTMLDivElement pickUpPointElement = div()
                .add(Select.create("Pick up point")
                        .addOption(SelectOption.create("Al Qouz"))
                        )
                .asElement();



        Collapsible addressCollapsible = Collapsible.create(newAddressElement).collapse();
        Collapsible pickUpPointCollapsible = Collapsible.create(pickUpPointElement).collapse();

        radioGroup.addChangeHandler(radio -> {
            if("default".equals(radio.getValue())){
                addressCollapsible.collapse();
                pickUpPointCollapsible.collapse();
            }
            if("new".equals(radio.getValue())){
                addressCollapsible.expand();
                pickUpPointCollapsible.collapse();
            }

            if("pickup-point".equals(radio.getValue())){
                addressCollapsible.collapse();
                pickUpPointCollapsible.expand();
            }

        });

        element.appendChild(Style.of(Card.create()).get()
                .appendChild(radioGroup)
                .appendChild(newAddressElement)
                .appendChild(pickUpPointElement)
                .appendChild(Button.create("CONFIRM ADDRESS").setBackground(Color.ORANGE).block().large().addClickListener(evt -> {
                    if(nonNull(addressSelectionHandler)){
                        addressSelectionHandler.onAddressSelected(new Address());
                    }
                }))
                .asElement()
        );
    }

    @Override
    public HTMLDivElement asElement() {
        return element;
    }

    public void setAddressSelectionHandler(AddressSelectionHandler addressSelectionHandler) {
        this.addressSelectionHandler = addressSelectionHandler;
    }

    @FunctionalInterface
    public interface AddressSelectionHandler{
        void onAddressSelected(Address address);
    }

    public class Address {
        private String city;
        private String area;
        private String description;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
