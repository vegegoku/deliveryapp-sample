package org.dominokit.samples;

import elemental2.core.JsDate;
import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.column.Column;
import org.dominokit.domino.ui.header.BlockHeader;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.infoboxes.InfoBox;
import org.dominokit.domino.ui.row.Row;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.Style;
import org.jboss.gwt.elemento.core.IsElement;

import java.util.Date;

import static java.util.Objects.nonNull;
import static org.jboss.gwt.elemento.core.Elements.div;

public class ShipmentDateComponent implements IsElement<HTMLDivElement> {

    private String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    private HTMLDivElement element = div().asElement();
    private Column column = Column.columns2();
    private InfoBox selectedInfoBox;
    private Date selectedDate = new Date();
    private DateSelectionHandler dateSelectionHandler;

    public ShipmentDateComponent() {


        InfoBox plus1 = getInfoBox(1);
        plus1.getTitleElement().textContent = "TOMORROW";
        InfoBox plus2 = getInfoBox(2);
        InfoBox plus3 = getInfoBox(3);
        InfoBox plus4 = getInfoBox(4);
        InfoBox plus5 = getInfoBox(5);
        InfoBox plus6 = getInfoBox(6);


        element.appendChild(BlockHeader.create("CHANGE DATE").asElement());
        element.appendChild(Card.create()
                .appendChild(Row.create()
                        .addColumn(column.copy().addElement(plus1))
                        .addColumn(column.copy().addElement(plus2))
                )
                .appendChild(Row.create()
                        .addColumn(column.copy().addElement(plus3))
                        .addColumn(column.copy().addElement(plus4))
                )
                .appendChild(Row.create()
                        .addColumn(column.copy().addElement(plus5))
                        .addColumn(column.copy().addElement(plus6))
                )
                .appendChild(Button.createPrimary("Confirm Date").setBackground(Color.INDIGO)
                        .block()
                        .large()
                        .addClickListener(evt -> {
                            if(nonNull(dateSelectionHandler)){
                                dateSelectionHandler.onDateSelected(selectedDate);
                            }
                        })
                )
                .asElement());
    }

    private InfoBox getInfoBox(int plusDays) {
        JsDate todayPlus = todayPlusDays(plusDays);
        InfoBox infoBox = InfoBox.create(Icons.ALL.date_range(), days[todayPlus.getDay()].toUpperCase(), todayPlus.getDate() + "");
        infoBox
                .setHoverEffect(InfoBox.HoverEffect.ZOOM)
                .setIconColor(Color.RED)
                .setBackground(Color.WHITE)
                .setIconBackground(Color.WHITE)
                .removeShadow();
        infoBox.asElement().addEventListener("click", evt -> {
            if (nonNull(selectedInfoBox)) {
                selectedInfoBox.setIconBackground(Color.WHITE);
                selectedInfoBox.setIconColor(Color.RED);
            }
            selectedInfoBox = infoBox;
            selectedInfoBox.setIconBackground(Color.INDIGO);
            selectedInfoBox.setIconColor(Color.WHITE);
            selectedDate = new Date(new Double(todayPlus.getTime()).longValue());
        });


        return Style.of(infoBox)
                .setWidth("50%")
                .setMarginLeft("25%")
                .setProperty("cursor", "pointer").get();
    }

    private JsDate todayPlusDays(int days) {
        JsDate jsDate = new JsDate();
        jsDate.setDate(new JsDate().getDate() + days);
        return jsDate;
    }

    public void setDateSelectionHandler(DateSelectionHandler dateSelectionHandler) {
        this.dateSelectionHandler = dateSelectionHandler;
    }

    @Override
    public HTMLDivElement asElement() {
        return element;
    }

    @FunctionalInterface
    public interface DateSelectionHandler{
        void onDateSelected(Date date);
    }
}
