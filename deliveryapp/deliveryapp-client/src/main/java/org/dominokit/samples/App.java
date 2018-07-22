package org.dominokit.samples;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLElement;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.style.ColorScheme;
import org.dominokit.domino.ui.style.Style;
import org.dominokit.domino.ui.utils.ElementUtil;

import static org.jboss.gwt.elemento.core.Elements.img;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

    private DeliveryManagementComponent deliveryManagementComponent = new DeliveryManagementComponent();
    private ReceiverComponent receiverComponent = new ReceiverComponent();
    private ShipmentDateComponent shipmentDateComponent = new ShipmentDateComponent();
    private ShipmentAddressComponent shipmentAddressComponent = new ShipmentAddressComponent();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Layout layout = Layout.create("Delivery Manager").show(ColorScheme.INDIGO);
        Style.of(layout.getContentSection())
                .css("content-margin")
        .setMarginBottom("40px");

        layout.getContentPanel()
                .appendChild(deliveryManagementComponent.asElement());

        deliveryManagementComponent.getReceiverBox().asElement().addEventListener("click", evt -> {
            ElementUtil.clear(layout.getContentPanel());
            layout.getContentPanel().appendChild(receiverComponent.asElement());
        });

        deliveryManagementComponent.getWhenBox().asElement().addEventListener("click", evt -> {
            ElementUtil.clear(layout.getContentPanel());
            layout.getContentPanel().appendChild(shipmentDateComponent.asElement());
        });

        deliveryManagementComponent.getWhereBox().asElement().addEventListener("click", evt -> {
            ElementUtil.clear(layout.getContentPanel());
            layout.getContentPanel().appendChild(shipmentAddressComponent.asElement());
        });

        receiverComponent.setPersonSelectionHandler(person -> {
            deliveryManagementComponent.setReceiver(person);
            ElementUtil.clear(layout.getContentPanel());
            layout.getContentPanel().appendChild(deliveryManagementComponent.asElement());
        });

        shipmentDateComponent.setDateSelectionHandler(date -> {
            deliveryManagementComponent.setDate(date);
            ElementUtil.clear(layout.getContentPanel());
            layout.getContentPanel().appendChild(deliveryManagementComponent.asElement());
        });

        shipmentAddressComponent.setAddressSelectionHandler(address -> {
            ElementUtil.clear(layout.getContentPanel());
            layout.getContentPanel().appendChild(deliveryManagementComponent.asElement());
        });

    }
}
