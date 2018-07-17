package org.dominokit.samples;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.collapsible.Collapsible;
import org.dominokit.domino.ui.forms.Radio;
import org.dominokit.domino.ui.forms.RadioGroup;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.header.BlockHeader;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.style.Style;
import org.jboss.gwt.elemento.core.IsElement;

import static java.util.Objects.nonNull;
import static org.jboss.gwt.elemento.core.Elements.div;

public class ReceiverComponent implements IsElement<HTMLDivElement> {

    private HTMLDivElement element = div().asElement();
    private static final Person DEFAULT = new Person("Alaa Mourad", "Colleage", "0555266332");

    private Person otherPerson = new Person();

    private Person selectedPerson = DEFAULT;

    private PersonSelectionHandler personSelectionHandler = person -> {
    };

    public ReceiverComponent() {
        element.appendChild(BlockHeader.create("SELECT RECEIVER").asElement());

        TextBox receiverName = TextBox.create("Receiver name")
                .setLeftAddon(Icons.ALL.person())
                .setRequired(true);

        receiverName.getInputElement().addEventListener("input", evt -> otherPerson.setName(receiverName.getValue()));

        TextBox relationship = TextBox.create("Relationship")
                .setLeftAddon(Icons.ALL.device_hub());
        relationship.getInputElement().addEventListener("input", evt -> otherPerson.setRelationShip(relationship.getValue()));

        TextBox contactNo = TextBox.create("Contact No.")
                .setLeftAddon(Icons.ALL.phone())
                .setRequired(true);
        contactNo.getInputElement().addEventListener("input", evt -> otherPerson.setContactNumber(contactNo.getValue()));

        HTMLDivElement personInfoElement = div()
                .add(receiverName.asElement())
                .add(relationship.asElement())
                .add(contactNo.asElement())
                .asElement();
        Collapsible personInfoCollapsible = Collapsible.create(personInfoElement).collapse();

        RadioGroup receiver = RadioGroup.create("receiver");
        Radio defaultRadio = Radio.create("Alaa Mourad", "Alaa Mourad (Default)");
        Radio otherRadio = Radio.create("other", "Other");

        element.appendChild(Style.of(Card.create()).setPaddingTop("20px").get()
                .appendChild(receiver
                        .addRadio(defaultRadio
                                .check()
                                .withGap())
                        .addRadio(otherRadio.withGap())
                        .addChangeHandler(radio -> {
                            if ("other".equals(radio.getValue())) {
                                personInfoCollapsible.expand();
                            } else {
                                personInfoCollapsible.collapse();
                            }
                        }))
                .appendChild(personInfoElement)
                .appendChild(Button.createPrimary("Confirm receiver").block().large()
                        .addClickListener(evt -> {

                            if (defaultRadio.isChecked()) {
                                selectedPerson = DEFAULT;
                                informListeners();
                            } else {
                                if (receiverName.validate() && contactNo.validate()) {
                                    selectedPerson = otherPerson;
                                    informListeners();
                                }
                            }

                        }))

                .asElement());
    }

    private void informListeners() {
        if (nonNull(personSelectionHandler)) {
            personSelectionHandler.onPersonSelected(selectedPerson);
        }
    }


    @Override
    public HTMLDivElement asElement() {
        return element;
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public static class Person {
        private String name;
        private String relationShip;
        private String contactNumber;

        public Person() {
        }

        public Person(String name, String relationShip, String contactNumber) {
            this.name = name;
            this.relationShip = relationShip;
            this.contactNumber = contactNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRelationShip() {
            return relationShip;
        }

        public void setRelationShip(String relationShip) {
            this.relationShip = relationShip;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }
    }

    public void setPersonSelectionHandler(PersonSelectionHandler personSelectionHandler) {
        this.personSelectionHandler = personSelectionHandler;
    }

    @FunctionalInterface
    public interface PersonSelectionHandler {
        void onPersonSelected(Person person);
    }
}
